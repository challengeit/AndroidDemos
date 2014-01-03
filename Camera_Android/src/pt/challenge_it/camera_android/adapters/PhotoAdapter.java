package pt.challenge_it.camera_android.adapters;

import java.util.List;
import pt.challenge_it.camera_android.model.Photo;
import pt.challenge_it.camera_android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class represents the adapter to a list of locations
 * 
 * @author Challenge.IT
 */
public class PhotoAdapter extends ArrayAdapter<Photo>{

	private LayoutInflater _inflater;
	
	/**
	 * @param context of an Activity
	 * @param list of locations
	 */
	public PhotoAdapter(Context context, List<Photo> elems){
		super(context, R.layout.photo_row, elems);
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ValueHolder holder = new ValueHolder();
        //If the convertView is not yet constructed, affect the holder and save it in a Tag inside the view
		if(convertView == null){
            vi = _inflater.inflate(R.layout.photo_row, null);
            holder.image =  (ImageView)  vi.findViewById(R.id.photo_id);
            holder.name =  (TextView)  vi.findViewById(R.id.photo_name);
            holder.description = (TextView) vi.findViewById(R.id.photo_desc);
            holder.created_at = (TextView) vi.findViewById(R.id.photo_date);
            vi.setTag(holder);
        }
        else
        	holder = (ValueHolder) vi.getTag();
        	        
		Photo photo = getItem(position);	        
        // Setting all values in listview
		holder.image.setImageBitmap(photo.getImage());
        holder.name.setText(photo.getName());
        holder.description.setText(photo.getDescription());
        holder.created_at.setText(photo.dateToString(getContext().getString(R.string.date_mask)));
        return vi;
	}
	
	/**
	 * This class is used to save the properties of a row in the list view
	 */
	private static class ValueHolder{
		ImageView image;
		TextView name;
		TextView description;
		TextView created_at;
	}
}
