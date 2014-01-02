package pt.challenge_it.location_withdb_android;

import com.google.android.gms.maps.CameraUpdateFactory;
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
    private final float ZOOM = 17.0f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		latitude = getIntent().getDoubleExtra(getString(R.string.param_lat), DEFAULT_VALUE);
		longitude = getIntent().getDoubleExtra(getString(R.string.param_lon), DEFAULT_VALUE);
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
                        getString(R.string.error_loc_msg), Toast.LENGTH_SHORT)
                        .show();
            } else {
            	// customize map appearance
            	googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            	googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), ZOOM));
            	
            	// create marker
            	MarkerOptions marker = new MarkerOptions()
            								.position(new LatLng(latitude, longitude))
            								.title(getString(R.string.location));
            	 
            	// adding marker
            	googleMap.addMarker(marker);
            	
            }
        }
	}
}
