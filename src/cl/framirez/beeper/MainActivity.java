/** 
Copyright 2014 Fabián Ramírez Barrios
**/

package cl.framirez.beeper;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cl.framirez.beeper.R;

public class MainActivity extends Activity implements LocationListener{

	public LocationManager locationManager;
	public TextView latitude;
	public TextView longitude,accuracy,speed,altitude,pro;
	public SendData senddata;
	public Button btnconnect;
	public EditText hostname;
	public double dlatitude,dlongitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		latitude = (TextView) findViewById(R.id.latitude);
		longitude = (TextView) findViewById(R.id.longitude);
		accuracy = (TextView) findViewById(R.id.accuracy);
		speed = (TextView) findViewById(R.id.speed);
		altitude = (TextView) findViewById(R.id.altitude);
		pro = (TextView) findViewById(R.id.provider);
		btnconnect = (Button) findViewById(R.id.send);
		hostname = (EditText) findViewById(R.id.et_hostname);
		//hostname.setText("http://192.168.2.18/~ramirez/testAddPoint.php");
		
		 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, this);
		
	}
	
	public void connect(View view){
		senddata = new SendData(this,hostname.getText().toString(),String.format("%9.6f",this.dlatitude),String.format("%9.6f",this.dlongitude));
		//Log.i("in connect: ", "fail");
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
		 switch (item.getItemId()) {
		 	case R.id.host1:
		 		hostname.setText("http://192.168.2.18/~ramirez/testAddPoint.php");
		 		return true;
		 	case R.id.host2:
		 		hostname.setText("http://172.16.57.132/~laost/Symfony/web/app_dev.php/addPoint/");
		 		return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		dlatitude = location.getLatitude();
		dlongitude = location.getLongitude();
		latitude.setText("Latitude: "+String.format("%9.6f",location.getLatitude())+"°");
		longitude.setText("Longitude: "+String.format("%9.6f",location.getLongitude())+"°");
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
