/**
 * 
 */
package pt.challenge_it.location_automatic_android;

import android.app.ListActivity;
import android.os.Bundle;
import pt.challenge_it.location_automatic_android.adapters.CustomLocationAdapter;
import pt.challenge_pt.location_automatic_android.R;

/**
 * This activity list all coordinates in database
 * 
 * @author Challenge.IT
 */
public class CoordinatesListActivity extends ListActivity{

	private CustomLocationAdapter adapter;
	private LocationApp application;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.list_coordinates));
		application = (LocationApp) getApplication();
		adapter = new CustomLocationAdapter(this, application.getCustomLocationManger().getAll());
		setListAdapter(adapter);
	}
}
