package com.rolflekang.kube95;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
	
	public ArrayList<Pant> parsePantekassa(String list) throws JSONException {
		ArrayList<Pant> tmp = new ArrayList<Pant>();
			JSONArray jsonArray = new JSONArray(list);
			Calendar cal = Calendar.getInstance();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				String[] d = json.getString("date").split("-");
				String user = json.getString("user");
				user = new JSONObject(user).getString("username");
				cal.set(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
				tmp.add(new Pant(cal.getTime(), Double.parseDouble(json.getString("amount")), user));				
			}
		return tmp;
	}
	public int[][] parseSwaps(String jsonString) throws JSONException {
		JSONArray jArray = new JSONArray(jsonString);
		int[][] swaps = new int[jArray.length()][2];
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jObject = jArray.getJSONObject(i);
			swaps[i][0] = jObject.getInt("oldweek");
			swaps[i][1] = jObject.getInt("newweek");
		}
		return swaps;
	}

}
