package pt.challenge_it.location_automatic_android.model;

/**
 * This class represents the concept of location in this project.
 * A location is represented by the provider and the coordinates.
 * 
 * @author Challenge.IT
 */
public class CustomLocation {

	public static final String AUTOMATIC_ORIGIN = "aut";
	public static final String MANUAL_ORIGIN = "man";
	
	private double latitude;
	private double longitude;
	private String provider;
	private String origin;

	/**
	 * Build the custom location object
	 * @param latitude
	 * @param longitude
	 * @param provider
	 * @param origin
	 */
	public CustomLocation(double latitude, double longitude, String provider, String origin) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.provider = provider;
		this.origin = origin;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}
}
