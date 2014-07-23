/** 
Copyright 2014 Fabián Ramírez Barrios
**/


package com.example.localizacion;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SendData extends AsyncTask<Void, Integer, Boolean>{
	
	private ProgressDialog mProgressDialog;
	private ProgressBar progressbar;
	private Context context;
	private String hostname;
	public SendData (Context context,ProgressBar b,String hn){
		this.context = context;
		this.progressbar = b;
		this.hostname = hn;
		this.mProgressDialog = new ProgressDialog(context);
		this.mProgressDialog.setMessage("Cargando... ");
		this.mProgressDialog.setIndeterminate(false);
		this.mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.mProgressDialog.setCancelable(true);
	}
	
	private void tareaLarga()
	{
	    try {
	        Thread.sleep(1000);
	    } catch(InterruptedException e) {}
	}
	
    @Override
    protected Boolean doInBackground(Void... params) {
    	/*json = new JSONObject();
    	try {
			json.put("latitud", "222");
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	HttpParams httpParams = new BasicHttpParams();
    	HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
    	HttpConnectionParams.setSoTimeout(httpParams, 10000);
    	HttpClient client = new DefaultHttpClient(httpParams);*/
    	
    	/*try {
    		String params = URLEncoder.encode("param1", "UTF-8")+ "=" + URLEncoder.encode("value1", "UTF-8");
    		params += "&" + URLEncoder.encode("param2", "UTF-8")+ "=" + URLEncoder.encode("value2", "UTF-8");
    		//String hostname = "mysite.com"
    		int port = 80;
    		InetAddress addr = InetAddress.getByName(hostname);
    		Socket socket = new Socket(addr, port);
    		String path = "/myapp";
    		//publishProgress(2*10);
    		// Send headers
    		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
    		wr.write("POST "+path+" HTTP/1.0rn");
    		wr.write("Content-Length: "+params.length()+"rn");
    		wr.write("Content-Type: application/x-www-form-urlencodedrn");
    		wr.write("rn");
    		//publishProgress(4*10);	 
            // Send parameters
            wr.write(params);
            wr.flush();
    			 
            // Get response
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            	}
    			             
            wr.close();
            rd.close();
    			             
	        }
	        catch (Exception e) {
	            e.printStackTrace();
    		}*/
    	
    	// Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(this.hostname);
        Log.i("SENDDATA", "Creacion host");
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("longitud", "13.555"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    	
    	
        /*for(int i=1; i<=10; i++) {
            tareaLarga();
 
            publishProgress(i*10);
 
            if(isCancelled())
                break;
        }*/
 
        return true;
    }
 
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progreso = values[0].intValue();
 
        progressbar.setProgress(progreso);
    }
 
    @Override
    protected void onPreExecute() {
    	progressbar.setMax(100);
    	progressbar.setProgress(0);
    	mProgressDialog.show();
    }
 
    @Override
    protected void onPostExecute(Boolean result) {
        if(result)
        	mProgressDialog.dismiss();
            Toast.makeText(this.context, "Tarea finalizada!",
                    Toast.LENGTH_SHORT).show();
        
    }
 
    @Override
    protected void onCancelled() {
        Toast.makeText(this.context, "Tarea cancelada!",
                Toast.LENGTH_SHORT).show();
    }
}
