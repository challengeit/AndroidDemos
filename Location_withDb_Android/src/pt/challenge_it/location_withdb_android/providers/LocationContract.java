package pt.challenge_it.location_withdb_android.providers;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * This class defines the contract to the location table.
 * Contains table name, columns and content URI for access via content resolver in Android.
 * 
 * @author Challenge.IT
 * @see ContentResolver 
 */
public class LocationContract {

	// Table name.
	public static final String TABLE = "LOCATION";
	
	// Columns names.
	public static final String _ID 	 = BaseColumns._ID,
							   LONGITUDE = "longitude",
							   LATITUDE = "latitude",
							   PROVIDER = "provider";
	
	// Content URI for subset of provided data from location provider.
	public static Uri CONTENT_URI = Uri.withAppendedPath(LocationProvider.CONTENT_URI, TABLE);
}
