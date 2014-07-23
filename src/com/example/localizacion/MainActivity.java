/** 
Copyright 2014 Fabián Ramírez Barrios
**/

package com.example.localizacion;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener{

	public LocationManager locationManager;
	public TextView latitud;
	public TextView longitud,accuracy,speed,altitude,pro;
	public ProgressBar bar;
	public SendData senddata;
	public Button btnconnect;
	public EditText hostname;
	public double dlatitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		latitud = (TextView) findViewById(R.id.latitude);
		longitud = (TextView) findViewById(R.id.longitude);
		accuracy = (TextView) findViewById(R.id.accuracy);
		speed = (TextView) findViewById(R.id.speed);
		altitude = (TextView) findViewById(R.id.altitude);
		pro = (TextView) findViewById(R.id.provider);
		btnconnect = (Button) findViewById(R.id.send);
		hostname = (EditText) findViewById(R.id.et_hostname);
		bar = (ProgressBar) findViewById(R.id.progressBar2);
		
		 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, this);
	     //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
		
	}
	
	public void connect(View view){
		btnconnect.setText(String.format("%9.6f",this.dlatitude));
		senddata = new SendData(this,bar,hostname.getText().toString());
		Log.i("in connect: ", "fail");
		senddata.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		dlatitude = location.getLatitude();
		latitud.setText("Latitude: "+String.format("%9.6f",location.getLatitude())+"°");
		longitud.setText("Longitude: "+String.format("%9.6f",location.getLongitude())+"°");
		accuracy.setText("Accuracy: "+String.format("%9.3f",location.getAccuracy())+"m");
		speed.setText("Speed: "+String.format("%9.2f",location.getSpeed())+"  m/s");
		altitude.setText("Altitude: "+String.format("%9.2f",location.getAltitude())+"m asl");
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	protected void onResume() {
	    super.onResume();
	    //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0, this);
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,200, 0, this);
	}
	
	@Override
	protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	}
	
	@Override
	protected void onDestroy() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	}


}
