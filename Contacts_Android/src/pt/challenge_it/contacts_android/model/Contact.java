package pt.challenge_it.contacts_android.model;

/**
 * The contact information
 * @author Challenge.IT
 */
public class Contact {
	private String name;
	private String phone_no;
	/**
	 * @param name
	 * @param phone_no
	 */
	public Contact(String name, String phone_no) {
		this.name = name;
		this.phone_no = phone_no;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the phone_no
	 */
	public String getPhone_no() {
		return phone_no;
	}
}