package com.androidbegin.jsonparsetutorial;

import it.sephiroth.android.library.widget.HListView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





















import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SingleItemView extends Activity {
	// Declare Variables
	String rank;
	String country;
	String population;
	String flag;
	String id,auth,link;	
	String position,cate;
  	
    ParallaxScrollView sv;
    
    String nid;
    

	
	Button submit;
	
		
	
	Context con;
	
	Intent i;
	
	
	Bundle extra,commentextra;
	
	String[] has=new String[10];
	
	
	
//	ListViewAdapter adapter;

	GridViewAdapter adapter;

	
	CommentAdapter commentadapter;
	
	ArrayList<HashMap<String, String>> arraylist;
	
	ArrayList<HashMap<String, String>> piclist;
	
	
	static String RANK = "date";
	static String COUNTRY = "title";
	static String POPULATION = "content";
	static String FLAG = "image";

	static String COMMENTS = "comments";
	
	
	
	
//	 private ListView mList;
	   
	private ListView mList;
	 
	HListView hList;
	
	Button back,options;
	
	String s;
	
	String listno;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.singleitemview);

		has[0]="aa";
		has[1]="aa";
		has[2]="aa";
		has[3]="aa";
		
		
		con=this;
		
		autoUpdate=new Timer();
		
		
		TextView tv;
		
		
		
		back=(Button)findViewById(R.id.menubutton);	
		
		options=(Button)findViewById(R.id.optionbutton);	
		
		
        arraylist = new ArrayList<HashMap<String, String>>();
		
		piclist = new ArrayList<HashMap<String, String>>();
		
		
	//	listview = (ListView) findViewById(R.id.listview);
		
		adapter = new GridViewAdapter(SingleItemView.this, arraylist,piclist);
		
		
		
//		mList = (ListView) findViewById(R.id.comview);
		
		
		
		
		extra=getIntent().getExtras();
		
	
		
		i = getIntent();
		// Get the result of rank
		rank = i.getStringExtra("rank");
		// Get the result of country
		country = i.getStringExtra("country");
		// Get the result of population
		population = i.getStringExtra("population");
		// Get the result of flag
		flag = i.getStringExtra("flag");
		
	
		id=i.getStringExtra("id");
		
		auth=i.getStringExtra("author");
		
		link=i.getStringExtra("link");
		
		nid=i.getStringExtra("nid");
		
		cate = i.getStringExtra("category");
		
		
		
	//	Intent cintent = new Intent(this, CommentActivity.class);
		
		
	//	cintent.putExtra("id",1);

	
		
		
		
	//	listno=i.getStringExtra("listno");
		
		listno=extra.getString("listno");
	
	/*	
		if (savedInstanceState == null) {
		    extra = getIntent().getExtras();
		    if(extra == null) {
		        listno= null;
		    } else {
		//        listno= listno.getString("listno");
		 
		        listno=i.getStringExtra("listno");
				
		    }
		} else {
		    listno= (String) savedInstanceState.getSerializable("STRING_I_NEED");
		}
		
		*/
	
	//	TextView textview=(TextView)findViewById(R.id.commen);
		
	//	textview.setPaintFlags(textview.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
		
		NewImageView img = (NewImageView) findViewById(R.id.flag);
		
		
		
		  Toast.makeText(con, ""+getResources().getConfiguration().orientation, Toast.LENGTH_SHORT).show();
		
		  
		  
		  sv = (ParallaxScrollView) findViewById(R.id.para);
		  
		  tv = (TextView) findViewById(R.id.header);
			
		  
		  tv.setText(cate);
		  
		  
		  if(getResources().getConfiguration().orientation==1){
		      
		    	DisplayMetrics displaymetrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
				int h = displaymetrics.heightPixels;
				int w = displaymetrics.widthPixels;
				
				android.view.ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
				layoutParams.width = w; 
				layoutParams.height =w;
				img.setAdjustViewBounds(true);
	
				
				
				hList = (HListView) findViewById(R.id.gridView1);
				
	
	}else{
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int h = displaymetrics.heightPixels;
		int w = displaymetrics.widthPixels;
		

		mList = (ListView) findViewById(R.id.gridView1);
		
		
		
		
		
//	TextView txtv=(TextView)findViewById(R.id.scr);
//	txtv.setText(Html.fromHtml(sv.getScrollX()+sv.getScrollY());
		
	//	this.setTitle(sv.getScrollX());
		
		
	//	LinearLayout layout = (LinearLayout)findViewById(R.id.word);
	//	// Gets the layout params that will allow you to resize the layout
		//android.view.ViewGroup.LayoutParams params = layout.getLayoutParams();
		// Changes the height and width to the specified *pixels*
		//params.height = 100;
	//	params.width = w-150;
		
		
		android.view.ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
		layoutParams.width = w-150;
		layoutParams.height =w-150;
		img.setAdjustViewBounds(true);
		
		
		
		
		
	}
	/*	
		android.view.ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
		layoutParams.width = w;
		layoutParams.height = w;
		img.setAdjustViewBounds(true);
		*/
		
	//img.setLayoutParams(layoutParams);
		
		
		
		
		
		
	//img.getLayoutParams().width = w;
	//img.getLayoutParams().height = w;
		
	//	img.setMinimumHeight(w);
	

		// Locate the TextViews in singleitemview.xml
		TextView txtrank = (TextView) findViewById(R.id.rank);
		TextView txtcountry = (TextView) findViewById(R.id.country);
		TextView txtpopulation = (TextView) findViewById(R.id.population);
		
		
		Coustxt author = (Coustxt) findViewById(R.id.author);
		
		final Coustxt lin = (Coustxt) findViewById(R.id.lin);
		
		
		
		
		
	
			    
			    
		
	  
		
		submit = (Button) findViewById(R.id.comsub);
		
		
		
		
		
		
		submit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
		//		if (et.getText().toString().length() < 1) {
		            // out of range
		 //           Toast.makeText(con, "please enter something", Toast.LENGTH_LONG).show();
		 //       } else 
				{
		 //           pb.setVisibility(View.VISIBLE);
		           //Old**   new MyAsyncTask().execute(et.getText().toString(),"1");
		        
		            
		            
		            
					  Intent in=new Intent(con,ComWeb.class);
						
					  
					  in.putExtra("id", id);
						
			            
			            con.startActivity(in);
		            
		            finish();
		             
		        	
		        }
				
				
				
				
				
			
				
			}
			
		});
		
		
		// Locate the ImageView in singleitemview.xml
//		NewImageView imgflag = (NewImageView) findViewById(R.id.flag);


		txtrank.setText(Html.fromHtml(rank));
		txtcountry.setText(Html.fromHtml(country));
		
		
		
		txtpopulation.setText(Html.fromHtml(population));
		
		txtpopulation.setMovementMethod(LinkMovementMethod.getInstance());
		   
		
	//	lin.setText(Html.fromHtml(link);
		
		
	
		   lin.setText(link);
		
	//	   lin.setMovementMethod(LinkMovementMethod.getInstance());
		   
		   
	//	   lin.setText(Html.fromHtml(Html.fromHtml("<a href=http://www.stackoverflow.com> STACK OVERFLOW "));
	//	    lin.setMovementMethod(LinkMovementMethod.getInstance());
		
	//	lin.setVisibility(View.GONE);
		
		  	
		    lin.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			    
					
					
					System.out.println("BLABLA : "+link);
					
					Intent intent ;
  //              	intent.setType("text/plain");
//                	// intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
//                	intent.putExtra(Intent.EXTRA_TEXT, link);

					intent = new Intent(Intent.ACTION_VIEW);
                    	
				//	intent.setData(Uri.parse("https://www.youtube.com/watch?v=66pcpCkxPLs"));
                	
					
					
					
						intent.setData(Uri.parse(link.toString()));
			
						
						try
						{
						if(!lin.getText().toString().equals(""))
				    startActivity(intent);
						}
						catch(Exception e)
						{
							System.out.println("An exception occured fpr act !!");
						}
				    
                	/*
                	// See if official Facebook app is found
                	boolean facebookAppFound = false;
                
                	
                	
                	List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
                	for (ResolveInfo info : matches) {
                	    if (info.activityInfo.packageName.toLowerCase().contains("youtube")) {
                	        intent.setPackage(info.activityInfo.packageName);
                	        facebookAppFound = true;
                	        break;
                	    }
                	}

                	// As fallback, launch sharer.php in a browser
                	if (!facebookAppFound) {
                	  //  String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                	    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                	    
                	}
*/
                   // intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    
            
                    	
                
				}
			});
		    
		
		if(auth!=null)
		{		
		if(!auth.equals(""))
		author.setText(Html.fromHtml(""+auth));
		else
		{
			author.setText(Html.fromHtml("Lusty Prince"));
		}
		}


		
	
		
		    
				
				
		try
		{
			Picasso.with(this).load(flag).into(img);
		}
		catch(IllegalArgumentException e){
			
			}
		
		
		
		final PopupMenu popupMenu = new PopupMenu(this, options); 

		popupMenu.inflate(R.menu.singleoptins);
     
     options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     popupMenu.show();
            
              	Intent intent = new Intent(android.content.Intent.ACTION_SEND); 
               intent.setType("text/plain");
                   intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                
               	String urlToShare = "http://whatyouwantmagazine.com/article.php?nid="+id;
 
               	intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

            	
         	   // intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToShare));
                
         	    
            startActivity(Intent.createChooser(intent, "Share via"));
		    
            }
        });

     
     
     
	
		
	}
	
	
	
	
	
	
	
	
	
	
	private PopupWindow pwindo;

	
	
	private OnClickListener cancel_button_click_listener = new OnClickListener() {
		public void onClick(View v) {
		pwindo.dismiss();

		}
		};
	
	
	
	
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
		
	/*		
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Android JSON Parse Tutorial");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();'
			
			*/
		}

		
		JSONObject jsonobject;
		
		JSONArray jsonarray;
		
		
		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<HashMap<String, String>>();
		
			piclist = new ArrayList<HashMap<String, String>>();
			
			
			
			postData("dgd");
			
			{
				
		
		
		jsonobject = JSONfunctions.getJSONfromURL("http://whatyouwantmagazine.com/chat.php?nwno=5&category=recent");

			
		Log.d("LOGEE", ""+jsonobject);
			
	//		noopus.net/jasontest/count.json
			
			try {
				// Locate the array name in JSON
				jsonarray = jsonobject.getJSONArray("noopazine");

			//	for (int i = jsonarray.length()-3; i < jsonarray.length(); i++) {
				
					for (int i = 0; i < 6; i++) {

						map = new HashMap<String, String>();
						
						HashMap<String,String> picmap = new HashMap<String, String>();
						
						
						if(isConnectedToInternet())
					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
				
					
			//		if(map.get("date")!=jsonobject.getString("date"))
			//			map.put("date", jsonobject.getString("date"));
					
					
			//		if(map.get("comments")!=jsonobject.getString("comments"))
			//			map.put("comments", jsonobject.getString("comments").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
				
					
					
					if(map.get("title")!=jsonobject.getString("title"))
						map.put("title", jsonobject.getString("title").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
					
		//			if(map.get("content")!=jsonobject.getString("content"))
		//				map.put("content", jsonobject.getString("content").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
					
		//			
				
					if(map.get("content")!=jsonobject.getString("content"))
						map.put("content", jsonobject.getString("content").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
				
					
					if(map.get("date")!=jsonobject.getString("date"))
						map.put("date", jsonobject.getString("date").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
				
					
			
					if(map.get("image")!=jsonobject.getString("image"))
						map.put("image", jsonobject.getString("image"));
				
				
					picmap.put("image", jsonobject.getString("image"));
					
					
		//			if(click)
				
				
			//		if(!click)
					piclist.add(picmap);
					
					// Set the JSON Objects into the array
				
					arraylist.add(map);
					
					
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			
			
			
			if(mList!=null)
			{		
			index = mList.getFirstVisiblePosition();
			v = mList.getChildAt(0);
			top = (v == null) ? 0 : v.getTop();
			}
			
			
			
			
			
			
			}
			// ...

			// restore
		
	curpox=sv.getScrollX();
			
			curpoy=sv.getScrollY();
		
			
			
			return null;
		}
		int index,top;
		
		View v;
		
		HashMap<String, String> map;
		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml
		//	listview = (ListView) findViewById(R.id.listview);
			
			  //   sv.scrollTo(top, top);
			
			 Toast.makeText(getApplicationContext(), "Loaded....!!!"
			 		+ "wow...!!!!",
	                    Toast.LENGTH_LONG).show();
			// Pass the results into ListViewAdapter.java
			
			 
				 
			 
			
	
			
			
			if(isConnectedToInternet())
			{		
				
			adapter = new GridViewAdapter(SingleItemView.this, arraylist,piclist);
			// Set the adapter to the ListView
		
		//	adapter.notifyDataSetChanged();
				
				
	
			
			
			if(hList!=null)
			{	
				
	
				
				
			hList.setAdapter(adapter);
			
			sv.post(new Runnable() { 
		        public void run() { 
		             sv.scrollTo((int)curpox, (int)curpoy);
		        } 
		});	
			
		//	setGridViewHeightBasedOnChildren(mList); 
			
			
		//	mList.setSelectionFromTop(index, top);
			
		//	hList.setSelection(top);
			
			}
			
			
			
			
			
			}
			// Close the progressdialog
		//	mProgressDialog.dismiss();
		}
	}
	
	
	  public void postData(String valueIWantToSend) {
          // Create a new HttpClient and Post Headeråå
          HttpClient httpclient = new DefaultHttpClient();
          HttpPost httppost = new HttpPost("http://whatyouwantmagazine.com/commentschat.php");

       try {
              // Add your data
              List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
              nameValuePairs.add(new BasicNameValuePair("myHttpData",valueIWantToSend));
              httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

              // Execute HTTP Post Request
              HttpResponse response = httpclient.execute(httppost);
 
              System.out.println("comment inserted :"+response);
              
          } catch (ClientProtocolException e) {
              // TODO Auto-generated catch block
          } catch (IOException e) {
              // TODO Auto-generated catch block
          }
      }
	  
	  
	

    float curpox,curpoy;
	
	
	
	 public boolean isConnectedToInternet(){
	        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	          if (connectivity != null) 
	          {
	              NetworkInfo[] info = connectivity.getAllNetworkInfo();
	              if (info != null) 
	                  for (int i = 0; i < info.length; i++) 
	                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
	                      {
	                          return true;
	                      }

	          }
	          return false;
	    }
	 
	 
	 
	 
	 
	 
	 
	 public void setListViewHeightBasedOnChildren(ListView listView) {
         ListViewAdapter listAdapter = (ListViewAdapter) listView.getAdapter(); 
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }

	 
	 
	 
	 
	 
	 public void setGridViewHeightBasedOnChildren(ListView lView) {
        
		 
		 GridViewAdapter GridAdapter = (GridViewAdapter) lView.getAdapter(); 
            if (GridAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            for (int i = 0; i < GridAdapter.getCount(); i++) {
                View GridItem = GridAdapter.getView(i, null, lView);
                GridItem.measure(0, 0);
                totalHeight += GridItem.getMeasuredHeight()/2;
            }

            ViewGroup.LayoutParams params = lView.getLayoutParams();
            params.height = totalHeight + (lView.getHeight() * (GridAdapter.getCount() - 1));
            lView.setLayoutParams(params);
            lView.requestLayout();
        }

	 
	 
	 
	 
	 
	 Timer autoUpdate;
	 @Override
	 public void onResume()
	 {
		 super.onResume();
		
		 
		 
		 System.out.println(population);
		 
		 new DownloadJSON().execute();
	
		// sv.scrollTo(-300, -300);
			
		 /*
		 autoUpdate.schedule(new TimerTask() {
		         @Override
		         public void run() {
		           runOnUiThread(new Runnable() {
		             public void run() {
		     	//		if(isConnectedToInternet())     				
		         //      new DownloadJSON().execute(); // this is the class that downloads the data from the server.
		           
		            	if(isConnectedToInternet())     				
		            		new DownloadJSON().execute(); // this is the class that downloads the data from the server.
				           
		            		// 	alertDialog.show();
		  			             
		             }
		           });
		         }
		       }, 0, 2000);
		       */
	 }
	 
	 
	 public void onConfigurationChanged(Configuration newConfig) {

		 ImageView img = (ImageView) findViewById(R.id.flag);
		 
		 
		    super.onConfigurationChanged(newConfig);
		    if(this.getRequestedOrientation()==Configuration.ORIENTATION_PORTRAIT){
		      
		    	DisplayMetrics displaymetrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
				int h = displaymetrics.heightPixels;
				int w = displaymetrics.widthPixels;
				
				android.view.ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
				layoutParams.width = w;
				layoutParams.height =w;
				img.setAdjustViewBounds(true);
		    	
		    	
		    	
		    }else if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
		      
		    	
		    	DisplayMetrics displaymetrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
				int h = displaymetrics.heightPixels;
				int w = displaymetrics.widthPixels;
				
				android.view.ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
				layoutParams.width = 500;
				layoutParams.height =500;
				img.setAdjustViewBounds(true);
				
		    	
		    	
		    	
		    }
		}
	 
	 public void back(View v){
			
			
			
			onBackPressed();
		
		}
	 
	
	
	 
	 @Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {
			 
			  case R.id.menu_home:
              {
              	
              	//  header.setText(Html.fromHtml("Recent");
              		
              	  
              	finish();
              }
              
              break;
              
              
              case R.id.menu_sub:
              {
             
       //       	 Intent in=new Intent(con,submitcontents.class);
					  
			  //          con.startActivity(in);
		        
              	 Intent in=new Intent(SingleItemView.this,ArticleSubmit.class);
         		  
                   startActivity(in);
                   
                   finish();

			            
              }
              break;
    
              
              case R.id.menu_photo:
              {
             
       //       	 Intent in=new Intent(con,submitcontents.class);
					  
			  //          con.startActivity(in);
		        
              	 Intent in=new Intent(SingleItemView.this,Submitphoto.class);
         		  
                   startActivity(in);

			            
              }
              break;
    
                   
	      }
			return true;	
		}
		
	 
	 
}