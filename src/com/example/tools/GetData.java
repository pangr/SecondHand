package com.example.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class GetData {
	//private URL url;
	private String url;
	private String line = null;
	private String str = null;
	
	public GetData(String url){
		this.url=url;
	}
	
	public String Deal() throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			str= EntityUtils.toString(entity, "UTF-8");
		
		
		
		}
		return str; 
	}
	
}
