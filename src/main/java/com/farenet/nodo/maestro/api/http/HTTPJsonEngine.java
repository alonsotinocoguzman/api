package com.farenet.nodo.maestro.api.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.farenet.nodo.maestro.api.util.StatusHttp;

public class HTTPJsonEngine {
	
	private static int timeout = 800000;

	public static String getJSONHttpGet(String url) throws Exception {

		HttpClient httpclient = new DefaultHttpClient();
		HttpParams params = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeout);
		HttpConnectionParams.setSoTimeout(params, timeout);
		HttpGet httpget = new HttpGet(url);

		HttpResponse response = httpclient.execute(httpget);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED
				|| response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
			throw new SecurityException("Acceso denegado ");

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new HttpException("Error en llamada al servidor " + response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");

		return responseString;
	}
	
	public static StatusHttp postJSONHttp(String url, Map<String, String> headers, String data) throws Exception {
				
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		HttpParams params = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeout);
		HttpConnectionParams.setSoTimeout(params, timeout);

		StringBuilder builder = new StringBuilder();
		
		StringEntity se = new StringEntity(data,HTTP.UTF_8);
		se.setContentType("application/json");
        httppost.setEntity(se);
        
        addHeaders(httppost, headers);
        
        // Execute HTTP Post Request
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        
	    InputStream content = entity.getContent();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	    String line;
	    while ((line = reader.readLine()) != null) {
	    	builder.append(line);
	        }
	    
	    StatusHttp statusHttp = generarStatus(response,builder);
	        
	        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED ||
	        		response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
	        	throw new SecurityException("Acceso denegado " );
	        
	        if( !(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
	        		|| response.getStatusLine().getStatusCode() == HttpStatus.SC_MULTIPLE_CHOICES
	        		|| response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED
	        		|| response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY))
	        {
	        	throw new HttpException("Error del servidor " + response.getStatusLine() + builder.toString()+ " - Json : " + data );
	        }
	        
	 
	  return statusHttp;
	}

	public static String putJSONHttp(String url, Map<String, String> headers, String data) throws Exception {
		
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpParams params = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeout);
		HttpConnectionParams.setSoTimeout(params, timeout);
		HttpPut httppost = new HttpPut(url);
		StringBuilder builder = new StringBuilder();
		
		StringEntity se = new StringEntity(data,HTTP.UTF_8);
		se.setContentType("application/json");
        httppost.setEntity(se);
        
        addHeaders(httppost, headers);
        
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	    	HttpEntity entity = response.getEntity();

	        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED||
	        		response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
	        	throw new SecurityException("Acceso denegado " );
	        
	        if( !(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
	        		|| response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY))
	        {
	        	throw new HttpException("Error del servidor");
	        }
	        	
	        
	        InputStream content = entity.getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
	        String line;
	        while ((line = reader.readLine()) != null) {
	          builder.append(line);
	        }
	      
	 
	  return builder.toString();
	}
	
	
	
	private static void addHeaders(HttpRequestBase httpCall,Map<String, String> headers)
	{
		
		if(headers!=null)
		{
			for(String key : headers.keySet())
			{
				httpCall.addHeader(key, headers.get(key));
			}
		}
		
	}
	
	private static StatusHttp generarStatus(HttpResponse response, StringBuilder builder) {
		StatusHttp statusHttp = new StatusHttp();
		statusHttp.setStatuscode(response.getStatusLine().getStatusCode());
		statusHttp.setStatus(response.getStatusLine().toString());
		statusHttp.setMessage(builder.toString());
		
		return statusHttp;
	}
	
}
