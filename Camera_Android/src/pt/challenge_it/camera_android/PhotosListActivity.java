package pt.challenge_it.camera_android;

import pt.challenge_it.camera_android.R;
import pt.challenge_it.camera_android.adapters.PhotoAdapter;
import pt.challenge_it.camera_android.providers.PhotoManager;
import android.app.ListActivity;
import android.os.Bundle;

/**
 * This activity list all the photos in database
 * 
 * @author Challenge.IT
 * @see ListActivity
 */
public class PhotosListActivity extends ListActivity {

	private PhotoAdapter adapter;
	private PhotoManager manager;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.list_photos));
		manager = new PhotoManager(this);
		adapter = new PhotoAdapter(this, manager.getAll());
		setListAdapter(adapter);
	}
}
