package pt.challenge_it.camera_android.model;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

/**
 * This class represents a photo
 * 
 * @author Challenge.IT
 */
public class Photo {

	Bitmap image;
	String description;
	
	/**
	 * @param image
	 * @param description
	 */
	public Photo(Bitmap image, String description) {
		this.image = image;
		this.description = description;
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
}
