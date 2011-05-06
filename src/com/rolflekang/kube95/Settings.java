package com.rolflekang.kube95;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Settings {
	private final String NODETITLEUSERNAME = "username";
	
	private Preferences pref;
	
	public Settings() {
		pref = Preferences.userRoot().node("kube95");
	}
	public String getUserName() {
		return pref.get(NODETITLEUSERNAME, "Name");
	}
	public void setUserName(String username) {
		pref.put(NODETITLEUSERNAME, username);
	}
	public boolean isUserNameSet(){
		try {
			if(pref.nodeExists(NODETITLEUSERNAME)) return true;
		} catch (BackingStoreException e) { return false; }
		return false;
	}
}
