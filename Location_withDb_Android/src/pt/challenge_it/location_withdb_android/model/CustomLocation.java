package pt.challenge_it.location_withdb_android.model;

/**
 * This class represents the concept of location in this project.
 * A location is represented by the provider and the coordinates.
 * 
 * @author Challenge.IT
 */
public class CustomLocation {

	private double latitude;
	private double longitude;
	private String provider;

	/**
	 * Build the custom location object
	 * @param latitude
	 * @param longitude
	 * @param provider
	 */
	public CustomLocation(double latitude, double longitude, String provider) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.provider = provider;
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
}
