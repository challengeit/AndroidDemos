package pt.challenge_it.camera_android.model;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

/**
 * This class represents a photo
 * 
 * @author Challenge.IT
 */
public class Photo {

	private Bitmap image;
	private String description;
	private String name;
	private Date created_at;
	
	/**
	 * @param image
	 * @param description
	 */
	public Photo(Bitmap image, String description, String name, Date created_at) {
		this.image = image;
		this.description = description;
		this.name = name;
		this.created_at = created_at;
	}

	/**
	 * @return the image
	 */
	public Bitmap getImage() {
		return image;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the created_at
	 */
	public Date getCreated_at() {
		return created_at;
	}

	/**
	 * Convert bitmap to byte array with JPEG compression
	 * @param bitmap
	 * @return byte array 
	 */
	public byte[] getBitmapAsByteArray() {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    image.compress(CompressFormat.JPEG, 0, outputStream);       
	    return outputStream.toByteArray();
	}
	
	/**
	 * Convert byte array to bitmap
	 * @param byte array
	 * @return bitmap
	 */
	public static Bitmap byteArrayToBitmap(byte[] array){
		return BitmapFactory.decodeByteArray(array, 0, array.length);
	}
	
	/**
	 * Convert long to Date
	 * @param time in milliseconds
	 * @return date
	 */
	public static Date longToDate(long millis){
		return new Date(millis);
	}
	
	/**
	 * Return a string with the date formatted to the parameter mask
	 * @param mask
	 * @return string with date formatted
	 */
	public String dateToString(String mask){
		SimpleDateFormat dateFormat = new SimpleDateFormat(mask, Locale.getDefault());
		return dateFormat.format(created_at);
	}
}
