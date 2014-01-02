package pt.challenge_it.location_automatic_android.adapters;

import java.util.List;
import pt.challenge_it.location_automatic_android.model.CustomLocation;
import pt.challenge_pt.location_automatic_android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * This class represents the adapter to a list of locations
 * 
 * @author Challenge.IT
 */
public class CustomLocationAdapter extends ArrayAdapter<CustomLocation>{

	private LayoutInflater _inflater;
	
	/**
	 * @param context of an Activity
	 * @param list of locations
	 */
	public CustomLocationAdapter(Context context, List<CustomLocation> elems){
		super(context, R.layout.location_row, elems);
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ValueHolder holder = new ValueHolder();
        //If the convertView is not yet constructed, affect the holder and save it in a Tag inside the view
		if(convertView == null){
            vi = _inflater.inflate(R.layout.location_row, null);
            holder.provider =  (TextView)  vi.findViewById(R.id.location_provider);
            holder.latitude = (TextView) vi.findViewById(R.id.location_latitude);
            holder.longitude = (TextView) vi.findViewById(R.id.location_longitude);
            holder.origin = (TextView) vi.findViewById(R.id.location_origin);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
        CustomLocation location = getItem(position);	        
        // Setting all values in listview
        holder.provider.setText(location.getProvider());
        holder.latitude.setText(String.valueOf(location.getLatitude()));
        holder.longitude.setText(String.valueOf(location.getLongitude()));
        holder.origin.setText(location.getOrigin());
        return vi;
	}
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		TextView provider;
		TextView latitude;
		TextView longitude;
		TextView origin;
	}
}
