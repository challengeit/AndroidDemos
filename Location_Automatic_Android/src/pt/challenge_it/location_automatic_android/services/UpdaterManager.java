package pt.challenge_it.location_automatic_android.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * This class represents a manager to handle the AlarmManager to user information service.
 * 
 * @author Challenge.IT
 **/
public class UpdaterManager {

	private AlarmManager manager;
	private PendingIntent operation;
	public static final int DEFAULT_DELAY = 1;
	
	/**
	 * Updater Manager constructor.
	 * @param context
	 */
	public UpdaterManager(Context context){
		manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		operation = PendingIntent.getService(context, 0, new Intent(context, LocationService.class), 0);
	}
	
	/**
	 * Method to change the delay between updates
	 * @param delay in minutes
	 */
	public void setAlarmManagerDelay(int delay){ 
		manager.setRepeating(AlarmManager.RTC, 0, delay * 1000 * 60, operation);
	}
	
	/**
	 * Method to cancel the update service
	 */
	public void cancelAlarmManager(){
		manager.cancel(operation);
	}	
}
