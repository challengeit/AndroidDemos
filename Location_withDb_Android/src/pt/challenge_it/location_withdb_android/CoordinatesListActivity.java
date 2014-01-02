/**
 * 
 */
package pt.challenge_it.location_withdb_android;

import android.app.ListActivity;
import android.os.Bundle;
import pt.challenge_it.location_withdb_android.adapters.CustomLocationAdapter;
import pt.challenge_it.location_withdb_android.providers.CustomLocationManager;

/**
 * This activity list all coordinates in database
 * 
 * @author Challenge.IT
 */
public class CoordinatesListActivity extends ListActivity{

	private CustomLocationAdapter adapter;
	private CustomLocationManager manager;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.list_coordinates));
		manager = new CustomLocationManager(this);
		adapter = new CustomLocationAdapter(this, manager.getAll());
		setListAdapter(adapter);
	}
}
