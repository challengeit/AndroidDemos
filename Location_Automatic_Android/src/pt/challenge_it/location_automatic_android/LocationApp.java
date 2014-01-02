/**
 * 
 */
package pt.challenge_it.location_automatic_android;

import pt.challenge_it.location_automatic_android.providers.CustomLocationManager;
import pt.challenge_it.location_automatic_android.services.UpdaterManager;
import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

/**
 * This class represents the application. It's needed to keep the global application state.
 * 
 * @author Challenge.IT
 */
public class LocationApp extends Application {

	private UpdaterManager updaterManager;
	private CustomLocationManager customLocationManger;
	private LocationManager locationManager;
	
	@Override
	public void onCreate(){
		customLocationManger = new CustomLocationManager(this);
		updaterManager = new UpdaterManager(this);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}

	/**
	 * @return the updaterManager
	 */
	public UpdaterManager getUpdaterManager() {
		return updaterManager;
	}

	/**
	 * @return the customLocationManger
	 */
	public CustomLocationManager getCustomLocationManger() {
		return customLocationManger;
	}

	/**
	 * @return the locationManager
	 */
	public LocationManager getLocationManager() {
		return locationManager;
	}
}
