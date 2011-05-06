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
		try {
			if(pref.nodeExists(NODETITLEUSERNAME)) return pref.get(NODETITLEUSERNAME, "Name");
			else return null;
		} catch (BackingStoreException e) { return null; }
	}
	public void setUserName(String username) {
		pref.put(NODETITLEUSERNAME, username);
	}
}
