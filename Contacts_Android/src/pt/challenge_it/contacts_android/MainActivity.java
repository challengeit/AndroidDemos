package pt.challenge_it.contacts_android;

import java.util.ArrayList;
import pt.challenge_it.contacts_android.adapters.ContactAdapter;
import pt.challenge_it.contacts_android.model.Contact;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.database.Cursor;

public class MainActivity extends ListActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); 
		new FetchContactsAsyncTask().execute();
	}
	
	/**
	 * {@link AsyncTask} for make the query to get the contacts.
	 * @author Challenge.IT
	 * */
	private class FetchContactsAsyncTask extends AsyncTask<Void, Void, ArrayList<Contact>>
	{
		@Override
		protected ArrayList<Contact> doInBackground(Void... params) 
		{
			ArrayList<Contact> contacts = new ArrayList<Contact>();
			
			// Get the persons from persons database.
			Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
			while(cursor.moveToNext())
			{
				String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				
				if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) == 0)
					continue;
				
				// Get the phone number from phones database.
				Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
																null,
																ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
																new String[]{ id }, null);
				while (phoneCursor.moveToNext()) 
				{
					String phone_no = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					contacts.add(new Contact(name, phone_no));
				}
				phoneCursor.close();
			}
			cursor.close();
			
			return contacts;
		}
		
		@Override
		protected void onPostExecute(ArrayList<Contact> contacts) 
		{
			// Set adapter in the list.
			ArrayAdapter<Contact> adapter = new ContactAdapter(MainActivity.this, contacts);
			MainActivity.this.setListAdapter(adapter);
		}
	}
}
