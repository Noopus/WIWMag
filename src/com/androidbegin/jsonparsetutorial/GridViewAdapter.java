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

import android.app.Activity;
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

public class GridViewAdapter extends BaseAdapter {

	// Declare Variables
	Activity context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	
	ArrayList<HashMap<String, String>> imagedata;
	
	HashMap<String, String> resultp = new HashMap<String, String>();

	HashMap<String, String> peepee = new HashMap<String, String>();

	
	int listno=1;
	
	
	public GridViewAdapter(Activity context,ArrayList<HashMap<String, String>> arraylist,ArrayList<HashMap<String, String>> piclist) {
		this.context = context;
		data = arraylist;
	
		imagedata = piclist;
		
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
	    
	  
	  
	  
	
	
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank;
		ImageView flag;
		
		
		
		
	
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.griditem, parent, false);
		// Get the position
		
		resultp = data.get(position);

		
		peepee = imagedata.get(position);
		
		
		// Locate the TextViews in listview_item.xml
		rank = (TextView) itemView.findViewById(R.id.head);
	
		// Locate the ImageView in listview_item.xml
		flag = (ImageView) itemView.findViewById(R.id.headimg);

		// Capture position and set results to the TextViews
		rank.setText(resultp.get(MainActivity.COUNTRY));
		
		//population.setText(resultp.get(MainActivity.POPULATION));
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
//		imageLoader.DisplayImage(peepee.get(MainActivity.FLAG), flag);

		
	//	Picasso f;
		
		if(peepee.get(MainActivity.FLAG)!=null&&!peepee.get(MainActivity.FLAG).equals(""))			
		Picasso.with(context).load(peepee.get(MainActivity.FLAG)).into(flag);
		
	 
		
		// Capture ListView item click
		
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data rank
				intent.putExtra("country", resultp.get(MainActivity.COUNTRY));
				
				
				intent.putExtra("rank", resultp.get("date"));
				
				intent.putExtra("population", resultp.get("content"));
			
				intent.putExtra("flag", resultp.get(MainActivity.FLAG));
					
				listno=position+1;
				
				
				//System.out.println("ajljgakgjakldjgladjgl   "+listno);
				
				Log.d(" LIST NO : ", "ajljgakgjakldjgladjgl   "+listno);
				
				
				intent.putExtra("listno", resultp.get(MainActivity.LISTNUM));
				
				
				
	//			new MyAsyncTask().execute("1");
				
				
				
				// Start SingleItemView Class
				context.startActivity(intent);
				
			    context.finish();

			}
		});
		return itemView;
	}
}








