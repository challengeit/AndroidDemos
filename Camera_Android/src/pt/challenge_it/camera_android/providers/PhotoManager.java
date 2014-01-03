package pt.challenge_it.camera_android.providers;

import java.util.ArrayList;
import pt.challenge_it.camera_android.model.Photo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * This class manages the access to database table Photo.
 * 
 * @author Challenge.IT
 */
public class PhotoManager {

	private Context context;
	
	/**
	 * Builds manager object.
	 * @param android activity context
	 */
	public PhotoManager(Context ctx){
		context = ctx;
	}
	
	/**
	 * Stores in database table Photo a new image tuple
	 * @param location 
	 */
	public void save(Photo photo){
		ContentValues values = new ContentValues();
		values.put(PhotoContract.IMAGE, photo.getBitmapAsByteArray());
		values.put(PhotoContract.DESCRIPTION, photo.getDescription());
		context.getContentResolver().insert(PhotoProvider.CONTENT_URI, values);
	}
	
	/**
	 * This method returns all photos saved persistently
	 * @return list of photos saved in database
	 */
	public ArrayList<Photo> getAll(){
		Cursor cursor = context.getContentResolver().query(PhotoProvider.CONTENT_URI, null, null, null, null);
		ArrayList<Photo> photos = new ArrayList<Photo>();
		//iterate all records and save then in array list
		while(cursor.moveToNext()){
			photos.add(new Photo(
								Photo.byteArrayToBitmap(cursor.getBlob(cursor.getColumnIndex(PhotoContract.IMAGE))),
								cursor.getString(cursor.getColumnIndex(PhotoContract.DESCRIPTION))
								)
						);
		}
		cursor.close();
		return photos;
	}
}
