package com.rolflekang.kube95;

import java.util.Date;

public class Pant {
	private Date date;
	private String user;
	private double value;
	
	public Pant(Date date, int value, String user){
		this.date = date;
		this.user = user;
		this.value = value;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() { return date; }

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() { return user; }

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() { return value; }
	
}
