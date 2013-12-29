package pt.challenge_it.location_android;

import pt.flag.android_training.location_android.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

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
	
	private void initializeMap(){
		if (googleMap == null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
		    SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager
		            .findFragmentById(R.id.map);
			googleMap = mapFragment.getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            } else {
            	// create marker
            	//MarkerOptions marker = new MarkerOptions()
            	//							.position(new LatLng(latitude, longitude))
            	//							.title("My Location");
            	 
            	// adding marker
            	//googleMap.addMarker(marker);
            	GoogleMapOptions options = new GoogleMapOptions();
            	options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(false)
                .rotateGesturesEnabled(false)
                .tiltGesturesEnabled(false);
            }
        }
	}
}
