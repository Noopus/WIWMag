package com.androidbegin.jsonparsetutorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	
	ArrayList<HashMap<String, String>> imagedata;
	
	
	
	
	
	HashMap<String, String> resultp = new HashMap<String, String>();

	HashMap<String, String> peepee = new HashMap<String, String>();

	
	int listno=1;
	
	
	
	
	public ListViewAdapter(Context context,ArrayList<HashMap<String, String>> arraylist,ArrayList<HashMap<String, String>> piclist) {
		this.context = context;
		data = arraylist;
	
		imagedata = piclist;
		

		category="";
		
	}

	
	@Override
	public int getCount() 
	{
		return data.size();
	}

	
	@Override
	public Object getItem(int position) {
		return null;
	}

	
	@Override
	public long getItemId(int position) {
		return 0;
	}

	
	
	
	
	
	  private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

	        @Override
	        protected Double doInBackground(String... params) {
	            // TODO Auto-generated method stub
	            postData(params[0]);
	            return null;
	        }

	        protected void onPostExecute(Double result) {
	    //        pb.setVisibility(View.GONE);
	     //       Toast.makeText(MainActivity.getApplicationContext(), "command sent",Toast.LENGTH_LONG).show();
	        }

	        protected void onProgressUpdate(Integer... progress) {
	   //         pb.setProgress(progress[0]);
	        }

	        public void postData(String valueIWantToSend) {
	            // Create a new HttpClient and Post Headeråå
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost("http://noopus.net/noopazine/commentschat.php");

	         try {
	                // Add your data
	                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	                nameValuePairs.add(new BasicNameValuePair("myHttpData",valueIWantToSend));
	                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	                // Execute HTTP Post Request
	                HttpResponse response = httpclient.execute(httppost);

	            } catch (ClientProtocolException e) {
	                // TODO Auto-generated catch block
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	            }
	        }

	    }
	    
	  
	  
	  
	boolean isphoto=false;
	
	
	String category;
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank;
		TextView country;
		Coustxt comments;
		Coustxt population;
		ImageView flag;
		
		category="";
		
		
	
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
		
		resultp = data.get(position);

		
		peepee = imagedata.get(position);
		
		
		rank = (TextView) itemView.findViewById(R.id.rank);
		
		country = (TextView) itemView.findViewById(R.id.country);
	
		population = (Coustxt) itemView.findViewById(R.id.population);

		comments = (Coustxt) itemView.findViewById(R.id.commen);

		
		flag = (ImageView) itemView.findViewById(R.id.flag);

	//	rank.setText(resultp.get(MainActivity.RANK));
		
		country.setText(resultp.get(MainActivity.COUNTRY));
		
		
		if(peepee.get(MainActivity.FLAG)!=null&&!peepee.get(MainActivity.FLAG).equals(""))
		Picasso.with(context).load(peepee.get(MainActivity.FLAG)).into(flag);
		
	 
		
		// Capture ListView item click
		
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				
				
				category=resultp.get("category");

				if(category.trim().equals("Photography"))
				{
					isphoto=true;
				}
				else
				{
					isphoto=false;
				}
				
				Intent intent ;//= new Intent(context, SingleItemView.class);
				
			//	intent = new Intent(context, photography.class);
				
				
				if(isphoto)
				{
					intent = new Intent(context, photography.class);
				}
				else
					intent = new Intent(context, SingleItemView.class);
					
				
				
				
				// Pass all data rank
				intent.putExtra("rank", resultp.get(MainActivity.RANK));
				// Pass all data country
				intent.putExtra("country", resultp.get(MainActivity.COUNTRY));
				// Pass all data population
				intent.putExtra("population",resultp.get(MainActivity.POPULATION));
				
			
				intent.putExtra("flag", resultp.get(MainActivity.FLAG));
				
				intent.putExtra("id", resultp.get("id"));
				
				intent.putExtra("tags", resultp.get("tags"));
				
				intent.putExtra("author", resultp.get("author"));
				
				intent.putExtra("link", resultp.get("link"));
				
				intent.putExtra("category", resultp.get("category"));
				
				
				//System.out.println("ajljgakgjakldjgladjgl   "+listno);
				
				listno=position+1;
				
				Log.d(" LIST NO : ", "ajljgakgjakldjgladjgl   "+listno);
				
				
				
				
	//			new MyAsyncTask().execute("1");
				
				
				
				// Start SingleItemView Class
				
				
			
				
				
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}








