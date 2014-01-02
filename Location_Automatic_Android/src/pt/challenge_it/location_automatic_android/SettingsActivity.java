package pt.challenge_it.location_automatic_android;

import pt.challenge_it.location_automatic_android.services.UpdaterManager;
import pt.challenge_pt.location_automatic_android.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * This activity represents the user configurations to automatic updates
 *  
 * @author Challenge.IT
 */
public class SettingsActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

	private static final String ONOFF_KEY = "set_onoff";
	private static final String DELAY_KEY = "set_delay";
	private LocationApp application;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setTitle(R.string.set_title);
		application = (LocationApp) getApplication();
		addPreferencesFromResource(R.xml.settings);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		preferences.registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if(((key.equals(ONOFF_KEY) || key.equals(DELAY_KEY)) && sharedPreferences.getBoolean(ONOFF_KEY, false))){
			application.getUpdaterManager().setAlarmManagerDelay(Integer.parseInt(sharedPreferences.getString(DELAY_KEY, UpdaterManager.DEFAULT_DELAY+"")));
		} else{
			application.getUpdaterManager().cancelAlarmManager();
		}
	}
}
