package pt.challenge_it.location_automatic_android;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pt.challenge_it.location_automatic_android.providers.LocationContract;
import pt.challenge_pt.location_automatic_android.R;

/**
 * This activity list all coordinates in database
 * 
 * @author Challenge.IT
 */
public class CoordinatesListActivity extends ListActivity{

	private CursorAdapter _adapter;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.list_coordinates));
		new FetchLocationsAsyncTask().execute();
	}
	
	private class CustomLocationsAdapterV2 extends CursorAdapter
	{
		
		public CustomLocationsAdapterV2(Cursor cursor)
		{
			super(CoordinatesListActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		}

		@Override
		public boolean areAllItemsEnabled() 
		{
			return true;
		}

		@Override
		public boolean isEnabled(int position) 
		{
			return true;
		}

		@Override
		public void bindView(View v, final Context ctx, final Cursor cursor)
		{
			((TextView) v.findViewById(R.id.location_provider))
				.setText(cursor.getString(cursor.getColumnIndex(LocationContract.PROVIDER)));
			((TextView) v.findViewById(R.id.location_latitude))
				.setText(cursor.getString(cursor.getColumnIndex(LocationContract.LATITUDE)));
			((TextView) v.findViewById(R.id.location_longitude))
				.setText(cursor.getString(cursor.getColumnIndex(LocationContract.LONGITUDE)));
			((TextView) v.findViewById(R.id.location_origin))
				.setText(cursor.getString(cursor.getColumnIndex(LocationContract.ORIGIN)));
		}

		@Override
		public View newView(Context ctx, Cursor cursor, ViewGroup vg) 
		{
			return getLayoutInflater().inflate(R.layout.location_row, null);
		}

		@Override
		protected void onContentChanged()
		{
			super.onContentChanged();
			// If notified of changes asks to fetch locations again.
			new FetchLocationsAsyncTask().execute();
		}
	}

	private class FetchLocationsAsyncTask extends AsyncTask<Void, Void, Cursor>
	{
		// Run in alternative Thread.
		@SuppressWarnings("deprecation")
		@Override
		protected Cursor doInBackground(Void... params)
		{
			// Make the query. Start managing cursor and return it.
			Cursor cursor = getContentResolver().query(LocationContract.CONTENT_URI, null, null, null, null);

			cursor.setNotificationUri(getContentResolver(), LocationContract.CONTENT_URI);
			// Tells that the MailActivity controls the life-cycle of the cursor.
			startManagingCursor(cursor);
			return cursor;
		}

		// Run in UI Thread.
		@SuppressWarnings("deprecation")
		@Override
		protected void onPostExecute(Cursor newCursor)
		{
			// If is the first time, creates the adapter.
			if(_adapter == null)
			{
				_adapter = new CustomLocationsAdapterV2(newCursor);
				// Set adapter in the list.
				CoordinatesListActivity.this.setListAdapter(_adapter);
			}
			// If the adapter already exists, swap the cursor in adapter.
			else
			{
				// Stop using the old version of the locations in the cursor.
				stopManagingCursor(_adapter.getCursor());
				_adapter.changeCursor(newCursor);
			}
		}
    }
}
