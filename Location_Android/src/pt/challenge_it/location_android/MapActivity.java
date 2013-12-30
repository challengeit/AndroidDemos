package pt.challenge_it.location_android;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
 
/**
 * This activity extends from FragmentActivity. The fragment implemented in this activity will 
 * support the view of the Google Map.
 * 
 * @author Challenge.IT
 * @see FragmentActivity
 **/
public class MapActivity extends FragmentActivity {

	// Google Map 
    private GoogleMap googleMap;
    private double latitude;
    private double longitude;
    private final double DEFAULT_VALUE = 0.0d;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		latitude = getIntent().getDoubleExtra("latitude", DEFAULT_VALUE);
		longitude = getIntent().getDoubleExtra("longitude", DEFAULT_VALUE);
		initializeMap();
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        initializeMap();
    }
	
	/**
	 * This method initialize the map and add location marker
	 **/ 
	private void initializeMap(){
		if (googleMap == null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
		    SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager
		            .findFragmentById(R.id.map);
			googleMap = mapFragment.getMap();
 
            // check if map is created successfully
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            } else {
            	// create marker
            	MarkerOptions marker = new MarkerOptions()
            								.position(new LatLng(latitude, longitude))
            								.title("My Location");
            	 
            	// adding marker
            	googleMap.addMarker(marker);
            }
        }
	}
}
