package com.rolflekang.kube95;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpConnector {

	public final int PANT = 0;

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
	public boolean sendPant(Date date, double amount, String user){
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		try {
			request.setURI(new URI(url+"pantekassa/a.php?key=0&date="+date.getYear()+"-"+date.getMonth()+"-"+date.getDate()+"&amount="+amount+"&user="+user));
			HttpResponse response = client.execute(request);
			response.getEntity().getContent();
		} catch (URISyntaxException e) { return false; } catch (ClientProtocolException e) { return false; } catch (IOException e) { return false; }
		return true;
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
	public int[][] getSwaps() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url+"pantekassa/s.html"));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = in.readLine()) != null) {
				String[] tmp = line.split("\\|");
				list.add(new int[]{Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1])});
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
		int[][] swaps = new int[list.size()][2];
		for (int[] is : list) {
			swaps[list.indexOf(is)] = is;
		}
		return swaps;
	}
	public boolean swap(int oldWeekNr, int newWeekNr) {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		try {
			request.setURI(new URI(url+"pantekassa/s.php?ow="+oldWeekNr+"&nw="+newWeekNr));
			HttpResponse response = client.execute(request);
			response.getEntity().getContent();
		} catch (URISyntaxException e) { return false; } catch (ClientProtocolException e) { return false; } catch (IOException e) { return false; }
		return true;
	}
}
