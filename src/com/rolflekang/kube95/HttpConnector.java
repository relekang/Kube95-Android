package com.rolflekang.kube95;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;


public class HttpConnector {

	public final int PANT = 0;
	public final int OLDSERVER = 0;
	public final int DJANGOSERVER = 1;


	private String url;
	private JsonParser jsonParser;
	private Settings settings;
	public HttpConnector(Context context, int server){
		jsonParser = new JsonParser();
		settings = new Settings(context);
		switch (server) {
		case OLDSERVER:
			this.url = "http://rolflekang.com/";
			break;
		case DJANGOSERVER:
			this.url =  "http://129.241.150.183:8000/api/";
			break;
		}
	}
	public ArrayList<Pant> getPant(){
		try {
			return jsonParser.parsePantekassa(getPant(0));
		} catch (JSONException e) { 
			e.printStackTrace(); 
			return null;
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
	private String getPant(int server) {
		String tmmp ="";
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url+"pantekassa/"));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = in.readLine()) != null) {
				tmmp += line;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				if (in != null) { try { in.close(); } catch (IOException e) { e.printStackTrace(); } }
			}
		}
		return tmmp;
	}
	public int[][] getSwaps() {
		String jsonString = "";
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(url+"swaps/"));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = in.readLine()) != null) {
				jsonString += line;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (in != null) { try { in.close(); } catch (IOException e) { e.printStackTrace(); } }
		}
		try {
			return jsonParser.parseSwaps(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean swap(int oldWeekNr, int newWeekNr) {
		swapWithPost(oldWeekNr, newWeekNr);
		return true;
//		try {
//			sendJsonRequest( url+"swaps/", jsonParser.createSwap(oldWeekNr, newWeekNr));
//			return true;
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//			return false;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		} catch (JSONException e) {
//			e.printStackTrace();
//			return false;
//		}
	}
	private void swapWithPost(int oldWeekNr, int newWeekNr) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url+"swaps/");

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("oldweek", Integer.toString(oldWeekNr)));
			nameValuePairs.add(new BasicNameValuePair("newweek", Integer.toString(newWeekNr)));
			nameValuePairs.add(new BasicNameValuePair("user", settings.getUserName() ));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
	} 
	public void sendJsonRequest(String url, JSONObject json) throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
		HttpResponse response;
		HttpPost post = new HttpPost(url);
		StringEntity se = new StringEntity( "JSON: " + json.toString());  
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(se);
		response = client.execute(post);
		/*Checking response */
		if(response!=null){
			String tmp = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = in.readLine()) != null) {
				tmp += line;
			}
			in.close();
		}
	}
}
