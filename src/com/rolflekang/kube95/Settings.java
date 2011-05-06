package com.rolflekang.kube95;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Settings {
	private final String NODETITLEUSERNAME = "username";
	
	private SharedPreferences pref;
	
	public Settings(Context context) {
		pref = PreferenceManager.getDefaultSharedPreferences(context);
	}
	public String getUserName() {
		String name = pref.getString(NODETITLEUSERNAME, "Name");
		if(!(name == "Name")) return name;
		else return null;
		
	}
	public void setUserName(String username) {
		Editor editor = pref.edit();
		editor.putString(NODETITLEUSERNAME, username);
		editor.commit();
	}
	public boolean isUserNameSet(){
			if(pref.contains(NODETITLEUSERNAME)) return true;
		return false;
	}
}
