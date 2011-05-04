package com.rolflekang.kube95;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Pantekassa extends ArrayList<Pant>{
	public Pantekassa() {
		
	}
	public double getSum() { 
		int sum = 0;
		for (Pant p : this) sum += p.getValue();
		return sum;
	}
}
