package info.ogorzalek.mosa.models;

import java.util.Map;

import com.google.gson.Gson;

public class Record {
	
	public long time;
	public String table;
	public Map<String, String> data;
	
	public static Record fromJSON(String data) {
		Gson gson = new Gson();
		Record ret =  gson.fromJson(data, Record.class);
		return ret;	
	}

	public String toJSON() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}	
	
}
