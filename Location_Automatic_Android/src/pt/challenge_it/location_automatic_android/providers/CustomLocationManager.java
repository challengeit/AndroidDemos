package pt.challenge_it.location_automatic_android.providers;

import java.util.ArrayList;

import pt.challenge_it.location_automatic_android.model.CustomLocation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * This class manages the access to database table Location.
 * 
 * @author Challenge.IT
 */
public class CustomLocationManager {

	private Context context;
	
	/**
	 * Builds manager object.
	 * @param android activity context
	 */
	public CustomLocationManager(Context ctx){
		context = ctx;
	}
	
	/**
	 * Stores in database table Location a new tuple of coordinates
	 * @param custom location 
	 */
	public void save(CustomLocation location){
		ContentValues values = new ContentValues();
		values.put(LocationContract.PROVIDER, location.getProvider());
		values.put(LocationContract.LATITUDE, location.getLatitude());
		values.put(LocationContract.LONGITUDE, location.getLongitude());
		values.put(LocationContract.ORIGIN, location.getOrigin());
		context.getContentResolver().insert(LocationProvider.CONTENT_URI, values);
	}
	
	/**
	 * This method returns all locations saved persistently
	 * @return list of locations saved in database
	 */
	public ArrayList<CustomLocation> getAll(){
		Cursor cursor = context.getContentResolver().query(LocationProvider.CONTENT_URI, null, null, null, null);
		ArrayList<CustomLocation> locations = new ArrayList<CustomLocation>();
		//iterate all records and save then in array list
		while(cursor.moveToNext()){
			locations.add(new CustomLocation(
								cursor.getDouble(cursor.getColumnIndex(LocationContract.LATITUDE)),
								cursor.getDouble(cursor.getColumnIndex(LocationContract.LONGITUDE)),
								cursor.getString(cursor.getColumnIndex(LocationContract.PROVIDER)),
								cursor.getString(cursor.getColumnIndex(LocationContract.ORIGIN))
								)
						);
		}
		cursor.close();
		return locations;
	}
}
