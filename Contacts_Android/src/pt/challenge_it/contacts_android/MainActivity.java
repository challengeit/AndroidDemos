package pt.challenge_it.contacts_android;

import java.util.ArrayList;
import pt.challenge_it.contacts_android.adapters.ContactAdapter;
import pt.challenge_it.contacts_android.model.Contact;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.ListActivity;
import android.database.Cursor;

public class MainActivity extends ListActivity {

	private ContactAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		adapter = new ContactAdapter(this, getAllContacts());
		setListAdapter(adapter);
	}

	/**
	 * get all contacts present in device
	 * @return contacts
	 */
	private ArrayList<Contact> getAllContacts(){
		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		while(cursor.moveToNext()){
			String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
			String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			
			if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
				Cursor phoneCursor = getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
						new String[]{ id }, null);
				while (phoneCursor.moveToNext()) {
					String phone_no = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					contacts.add(new Contact(name, phone_no));
				}
				phoneCursor.close();
			}
		}
		cursor.close();
		return contacts;
	}
}
