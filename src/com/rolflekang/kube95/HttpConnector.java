package com.rolflekang.kube95;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpConnector {

	public static final int PANT = 0;

	private String url;
	public HttpConnector(String ip){
		this.url =  "http://rolflekang.com/";// /kube95/api/list/";
	}
	public String[] getList(int listType){
		switch(listType){
		case PANT: return getPant();
		default: return null;
		}
	}
	private String[] getPant() {

		ArrayList<String> tmp = new ArrayList<String>();
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url+"pantekassa/index.html"));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = in.readLine()) != null) {
				tmp.add(line);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return (String[]) tmp.toArray(new String[tmp.size()]);
	}
}
