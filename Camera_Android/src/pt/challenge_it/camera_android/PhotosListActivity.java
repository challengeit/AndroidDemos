package pt.challenge_it.camera_android;

import pt.challenge_it.camera_android.R;
import pt.challenge_it.camera_android.asynctasks.BitmapWorker;
import pt.challenge_it.camera_android.model.Photo;
import pt.challenge_it.camera_android.providers.PhotoContract;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This activity list all the photos in database
 * 
 * @author Challenge.IT
 * @see ListActivity
 */
public class PhotosListActivity extends ListActivity {

	private CursorAdapter _adapter;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setTitle(getString(R.string.list_photos));
		new FetchPhotosAsyncTask().execute();
	}
	
	private class PhotoAdapterV2 extends CursorAdapter
	{
		
		public PhotoAdapterV2(Cursor cursor)
		{
			super(PhotosListActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
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
			new BitmapWorker((ImageView) v.findViewById(R.id.photo_id)).execute(cursor.getBlob(cursor.getColumnIndex(PhotoContract.IMAGE)));
			((TextView) v.findViewById(R.id.photo_name))
				.setText(cursor.getString(cursor.getColumnIndex(PhotoContract.NAME)));
			((TextView) v.findViewById(R.id.photo_desc))
				.setText(cursor.getString(cursor.getColumnIndex(PhotoContract.DESCRIPTION)));
			((TextView) v.findViewById(R.id.photo_date))
				.setText(Photo.dateToString(Long.parseLong(cursor.getString(cursor.getColumnIndex(PhotoContract.CREATED_AT))), getString(R.string.date_mask)));
		}

		@Override
		public View newView(Context ctx, Cursor cursor, ViewGroup vg) 
		{
			return getLayoutInflater().inflate(R.layout.photo_row, null);
		}
	}

	private class FetchPhotosAsyncTask extends AsyncTask<Void, Void, Cursor>
	{
		// Run in alternative Thread.
		@SuppressWarnings("deprecation")
		@Override
		protected Cursor doInBackground(Void... params)
		{
			// Make the query. Start managing cursor and return it.
			Cursor cursor = getContentResolver().query(PhotoContract.CONTENT_URI, null, null, null, null);

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
				_adapter = new PhotoAdapterV2(newCursor);
				// Set adapter in the list.
				PhotosListActivity.this.setListAdapter(_adapter);
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
