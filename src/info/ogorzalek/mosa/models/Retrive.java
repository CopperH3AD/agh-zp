package info.ogorzalek.mosa.models;

import info.ogorzalek.mosa.general.Backend;
import info.ogorzalek.mosa.general.Backend.OnHttpResponseListener;
import info.ogorzalek.mosa.general.Statics;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.ViewDebug.ExportedProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Retrive {
	
	public long start_time;
	
	public long end_time;
	
	
	private List<Record> records;
	
	private final static String ENDPOINT = "retrive";
	
	public static Retrive fromJSON(String data) throws JSONException {
		
		//japierdole
		JSONObject object = new JSONObject(data);
		
		Retrive retrive = new Retrive();
		retrive.start_time = object.getLong("start_time");
		retrive.end_time = object.getLong("end_time");
		retrive.records = new ArrayList<Record>();
		
		JSONArray arrayOfArrays = object.getJSONArray("records");
		for(int i = 0; i < arrayOfArrays.length(); i++) {
			JSONArray currentArray = arrayOfArrays.getJSONArray(i);
			for(int j = 0; j < currentArray.length(); j++) {
				
				JSONObject currentObject = currentArray.getJSONObject(j);
				
				Record record = new Record();
				record.table = currentObject.getString("table");
				record.time = currentObject.getLong("time");
				record.data = new HashMap<String, String>();
				
				JSONObject currentData = currentObject.getJSONObject("data");
				
				// za jakie grzechy?
				Iterator iterator = currentData.keys();
				
				while(iterator.hasNext()) {
					String key = (String) iterator.next();
					record.data.put(key, currentData.getString(key));
				}
				
				retrive.records.add(record);				
				
			}
		}
		
		return null;
	}
	
	public static void getTable(Backend backend, final OnRetriveListener listener, String table, long startTime, long endTime) {
		
		backend.get(new OnHttpResponseListener() {
			
			public void onResponse(JSONObject data, boolean fromCache) {
				Retrive object = null;
				try {
					object = Retrive.fromJSON(data.toString());
				} catch (JSONException e) {
					listener.onError(e);
					return;
				}
				listener.onRetrive(object);
			}
			
			public void onError(Exception e) {
				listener.onError(e);
			}
			
		}, getDetailUrl(table, startTime, endTime));
		
	}
	
	public static String getDetailUrl(String table, long startTime, long endTime) {
		String url = String.format("%s%s?table=%s&start_time=%d&end_time=%d", Statics.BASE_URL, ENDPOINT, table, startTime, endTime);
		return url;
	}
	
	public interface OnRetriveListener {
		public void onRetrive(Retrive retrive);
		public void onError(Exception e);
	}
	
}
