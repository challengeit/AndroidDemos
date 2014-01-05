package pt.challenge_it.contacts_android.adapters;

import java.util.List;
import pt.challenge_it.contacts_android.R;
import pt.challenge_it.contacts_android.model.Contact;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class represents the adapter to a list of contacts
 * 
 * @author Challenge.IT
 */
public class ContactAdapter extends ArrayAdapter<Contact>{

	private LayoutInflater _inflater;
	
	/**
	 * @param context of an Activity
	 * @param list of contacts
	 */
	public ContactAdapter(Context context, List<Contact> elems){
		super(context, R.layout.contact_row, elems);
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ValueHolder holder = new ValueHolder();
        //If the convertView is not yet constructed, affect the holder and save it in a Tag inside the view
		if(convertView == null){
            vi = _inflater.inflate(R.layout.contact_row, null);
            holder.name = (TextView) vi.findViewById(R.id.contact_name);
            holder.phone = (TextView) vi.findViewById(R.id.contact_phone);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
		Contact contact = getItem(position);	        
        // Setting all values in listview
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone_no());
        return vi;
	}
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		TextView name;
		TextView phone;
	}
}

