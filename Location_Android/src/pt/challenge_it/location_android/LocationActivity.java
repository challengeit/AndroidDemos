package pt.challenge_it.location_android;

import pt.flag.android_training.location_android.R;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity implements the interface LocationListener. This interface is used for
 * receiving notifications from the LocationManager when the device location is changed.
 * The LocationManager is a class that allows us access to the system location services.
 * 
 * @author diogo.matos
 * @see LocationListener
 * @see LocationManager
 **/
public class LocationActivity extends Activity implements LocationListener{

	private final int LOCATION_CHANGE_REQUEST_TIMER = 1000 * 60 * 5; //5 minutes
	private TextView longitude;
	private TextView latitude;
	private LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		setTitle(R.string.app_title);
		
		//setup fields
		longitude = (TextView) findViewById(R.id.longitudeText);
		latitude = (TextView) findViewById(R.id.latitudeText);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    
	    //setup buttons behavior
	    // - location button
  		findViewById(R.id.locationBtn).setOnClickListener(new OnClickListener() {		
  			@Override
  			public void onClick(View v) {
  				String provider = getSelectedProvider();
  				if(locationManager.isProviderEnabled(provider))
  					onLocationChanged(locationManager.getLastKnownLocation(provider));
  				else{
  					latitude.setText(R.string.location_error);
  					longitude.setText(R.string.location_error);
  				}
  			}
  		});
  		// - enabled button
  		findViewById(R.id.enabledBtn).setOnClickListener(new OnClickListener() {		
  			@Override
  			public void onClick(View v) {
  				Toast.makeText(LocationActivity.this
  						, locationManager.isProviderEnabled(getSelectedProvider()) ? "Enabled" : "Disabled"
  						, Toast.LENGTH_SHORT).show();
  			}
  		});
  		// - settings button
  		findViewById(R.id.settingsBtn).setOnClickListener(new OnClickListener() {		
  			@Override
  			public void onClick(View v) {
  				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
  			}
  		});
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		String provider = getSelectedProvider();
		//start location updates requests
		if(locationManager.isProviderEnabled(provider))
			locationManager.requestLocationUpdates(provider, LOCATION_CHANGE_REQUEST_TIMER, 1, this);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		//stop requests when activity is paused
		locationManager.removeUpdates(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		//update fields with coordinates
		longitude.setText(String.valueOf(location.getLongitude()));
		latitude.setText(String.valueOf(location.getLatitude()));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Called when the provider is disabled by the user.
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Called when the provider is enabled by the user.
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Called when the provider status changes.
	}
	
	/**
	 * Get the location from the selected provider
	 * @return string representative of the location provider
	 */
	private String getSelectedProvider(){
		String provider = "";
		//verify which radio button is selected and return the provider
		switch(((RadioGroup)findViewById(R.id.locProvider)).getCheckedRadioButtonId()){
			case R.id.prov_gps:
				provider = LocationManager.GPS_PROVIDER;
				break;
			case R.id.prov_network:
				provider = LocationManager.NETWORK_PROVIDER;
				break;
			case R.id.prov_passive:
				provider = LocationManager.PASSIVE_PROVIDER;
				break;
		}
		return provider;
	}
}
