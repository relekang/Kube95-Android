package com.rolflekang.kube95;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Paint.Join;


@SuppressWarnings("serial")
public class Pantekassa extends ArrayList<Pant>{
	
	public Pantekassa() {  }
	
	public void update(ArrayList<Pant> list) {
		this.clear();
		for (Pant pant : list) {
			this.add(pant);
		}
	}

	public double getSum() { 
		int sum = 0;
		for (Pant p : this) sum += p.getAmount();
		return sum;
	}
}
