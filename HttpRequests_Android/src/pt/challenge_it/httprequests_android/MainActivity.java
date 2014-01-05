package pt.challenge_it.httprequests_android;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;

/**
 * 
 * @author Challenge.IT
 * */
public class MainActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// find the button for set the click listener. When clicks gets the current Lisbon's weather.
		findViewById(R.id.btn_weather).setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// Executes the asynchronous task that will be make the HTTP request.
				new FetchWeatherDataAsyncTask().execute();
			}
		});
	}
	
	/**
	 * Concrete implementation of {@link AsyncTask} to make HTTP requests to "http://openweathermap.org/" 
	 * to get the current weather in Lisbon.
	 * @author Challenge.IT
	 * */
	private class FetchWeatherDataAsyncTask extends AsyncTask<Void, Void, String>
	{
		private final static String URI = "http://api.openweathermap.org/data/2.5/weather?q=Lisbon&mode=json&units=metric";
		private final static int STATUS_OK = 200;
		
		@Override
		protected String doInBackground(Void... params)
		{
			String res = "";
			try 
			{
				/*
				 *  Uses Apache HttpClient library to simplify the HTTP requests handling.
				 *  For more info, see the tutorial documentation:
				 *  http://hc.apache.org/httpcomponents-client-ga/tutorial/html/index.html
				 */
				HttpResponse response = new DefaultHttpClient().execute(new HttpGet(URI));
				
				if(response.getStatusLine().getStatusCode() != STATUS_OK)
					return "";
				
				// Get the body.
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line = "";
				while ((line = rd.readLine()) != null)
					res += line;
				
				// Parse the JSON body and get the weather field.
				JSONObject jsonObj = new JSONObject(res);
				Double temp = jsonObj.getJSONObject("main").getDouble("temp");
				
				res = temp + " ºC";
			} 
			catch (Exception e) { e.printStackTrace(); }
			
			return res;
		}
		
		@Override
		protected void onPostExecute(String result) 
		{ 
			// Set the temperature in the text field.
			if (result.equals(""))
				result = "Unable to get the weather from openweathermap";
			
			((TextView)findViewById(R.id.txt_weather)).setText(result.toString());
		}
	}
}