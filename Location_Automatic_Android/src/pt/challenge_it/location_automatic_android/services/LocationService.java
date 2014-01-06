/**
 * 
 */
package pt.challenge_it.location_automatic_android.services;

import pt.challenge_it.location_automatic_android.LocationApp;
import pt.challenge_it.location_automatic_android.model.CustomLocation;
import pt.challenge_it.location_automatic_android.providers.LocationContract;
import pt.challenge_pt.location_automatic_android.R;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.IBinder;
import android.preference.PreferenceManager;

/**
 * This class implements the android service to do automatic updates from user location
 * 
 * @author Challenge.IT
 */
public class LocationService extends Service {

	private static final String PROFILE_KEY = "set_provider";
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String provider = preferences.getString(PROFILE_KEY, getString(R.string.default_provider));
		LocationApp application = (LocationApp) getApplication();
		
		// save location if provider enabled and location is valid
		if(application.getLocationManager().isProviderEnabled(provider)){
			Location location = application.getLocationManager().getLastKnownLocation(provider);
			if(location != null){
				application.getCustomLocationManger().save(new CustomLocation(
						location.getLatitude(), 
						location.getLongitude(), 
						provider,
						CustomLocation.AUTOMATIC_ORIGIN
				));
				
				// trigger notification
				getContentResolver().notifyChange(LocationContract.CONTENT_URI, null);
			}
		}
		
		//Not Sticky means that the service will not start until the user open the application again
		//for example the Android needs memory and stop the services, this service will not start
		//when the resources are free
	    return START_NOT_STICKY;
	}  
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
