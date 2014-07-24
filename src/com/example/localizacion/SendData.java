/** 
Copyright 2014 Fabián Ramírez Barrios
 **/

package com.example.localizacion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendData extends AsyncTask<Void, Integer, Boolean> {

	private ProgressDialog mProgressDialog;
	private Context context;
	private String hostname;
	private String latitude,longitude;

	public SendData(Context context, String hn, String lt,String lg) {
		this.context = context;
		this.hostname = hn;
		this.latitude= lt;
		this.longitude=lg;
		this.mProgressDialog = new ProgressDialog(context);
		this.mProgressDialog.setMessage("Cargando... ");
		this.mProgressDialog.setIndeterminate(false);
		this.mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.mProgressDialog.setCancelable(true);
	}

	private void longworck() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	protected Boolean doInBackground(Void... params) {

		/*
		 * HttpResponse response = null; try { // Create http client object to
		 * send request to server HttpClient client = new DefaultHttpClient();
		 * // Create URL string String URL =
		 * "http://172.16.57.132/~laost/Symfony/web/app_dev.php/addPoint/?latitude=1234&longitude=4321"
		 * ; // Create Request to server and get response HttpGet httpget= new
		 * HttpGet(); httpget.setURI(new URI(URL)); response =
		 * client.execute(httpget); } catch (URISyntaxException e) {
		 * e.printStackTrace(); } catch (ClientProtocolException e) { // TODO
		 * Auto-generated catch block } catch (IOException e) { // TODO
		 * Auto-generated catch block }
		 */

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(this.hostname);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("longitude", this.longitude));
			nameValuePairs.add(new BasicNameValuePair("latitude", this.latitude));
			//Log.i("SENDDATA", "latitude1223");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// Execute HTTP Post Request
			longworck();
			HttpResponse response = httpclient.execute(httppost);
			int responseCode = response.getStatusLine().getStatusCode();
			longworck();
			switch(responseCode) {
				case 200:
					HttpEntity entity = response.getEntity();
					    if(entity != null) {
					        String responseBody = EntityUtils.toString(entity);
					        Log.i("SENDDATA", responseBody);
					    }
					    break;
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		return true;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		int progreso = values[0].intValue();
	}

	@Override
	protected void onPreExecute() {
		mProgressDialog.show();
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if (result)
			mProgressDialog.dismiss();
		Toast.makeText(this.context, "Tarea finalizada!", Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	protected void onCancelled() {
		Toast.makeText(this.context, "Tarea cancelada!", Toast.LENGTH_SHORT)
				.show();
	}
}
