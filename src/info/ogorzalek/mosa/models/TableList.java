package info.ogorzalek.mosa.models;

import info.ogorzalek.mosa.general.Backend;
import info.ogorzalek.mosa.general.Backend.OnHttpResponseListener;
import info.ogorzalek.mosa.general.Statics;

import java.util.List;
import org.json.JSONObject;

import com.google.gson.Gson;

public class TableList {
	
	public List<String> tables;
	
	private static final String ENDPOINT = "retrive";
	
	public static TableList fromJSON(String data) {
		Gson gson = new Gson();
		TableList ret =  gson.fromJson(data, TableList.class);
		return ret;	
	}

	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static void getTableList(Backend backend, final OnTableListListener listener) {
		backend.get(new OnHttpResponseListener() {
			
			public void onResponse(JSONObject data, boolean fromCache) {
				TableList list = TableList.fromJSON(data.toString());
				listener.onTableList(list);
			}
			
			public void onError(Exception e) {
				listener.onError(e);
			}
		}, getUrl());
	}
	
	private static String getUrl() {
		String url = String.format("%s%s", Statics.BASE_URL, ENDPOINT);
		return url;
	}
	
	public interface OnTableListListener {
		public void onTableList(TableList list);
		public void onError(Exception e);
	}
	
}
