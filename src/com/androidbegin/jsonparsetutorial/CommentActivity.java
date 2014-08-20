package com.androidbegin.jsonparsetutorial;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;








































import com.darvds.ribbonmenu.iRibbonMenuCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CommentActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener,iRibbonMenuCallback{
	// Declare Variables
	JSONObject jsonobject;
	JSONArray jsonarray;
	CommentAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	String buff,b2;
	
	ArrayList<HashMap<String, String>> piclist;
	
	
	static String RANK = "date";
	
	
	static String COMMENT = "cmts";
	
	static String USERNAME = "username";
	
	
	
	
	static String POPULATION = "content";
	static String FLAG = "image";

	//static String COMMENTS = "comments";
	
	
	

    private static final String KEY_TRANSITION_EFFECT = "transition_effect";

   // private JazzyListView mList;
    
    private ListView mList;
    
    private HashMap<String, Integer> mEffectMap;
   // private int mCurrentTransitionEffect = JazzyHelper.SLIDE_IN;

    
    Button but;
    
    boolean click=false;
    
    Context con;
    
    
    String id;
	
    Bundle extra;
	
    
    EditText edi;
    
    
    
    int cmno=10;
    
    
    ProgressBar spinner;
	
    
    
    Button menu;
    
    
    ImageView gear;
    
    

Resources res;
		


  SharedPreferences sharedpreferences;



  String username;
  
  
  
  SwipeRefreshLayout swipeLayout; 
		  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_Comment.xml
		
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		setContentView(R.layout.commentlistview);
		
		
		
		res=this.getResources();
		
		
		Drawable draw=res.getDrawable(R.drawable.custombar);
		
		
		
		
		
		RotateAnimation rotateAnimation1 = new RotateAnimation(0, 360,
		        Animation.RELATIVE_TO_SELF, 0.5f,
		        Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation1.setInterpolator(new LinearInterpolator());
		rotateAnimation1.setDuration(2000);
		rotateAnimation1.setRepeatCount(2000);
		
		
		
		
	      spinner = (ProgressBar)findViewById(R.id.progressBar1);
	      
		
	      spinner.setProgressDrawable(draw);
		
		
		but=(Button) this.findViewById(R.id.send);
		
		
		menu=(Button) this.findViewById(R.id.menubutton);
		
		
		
		gear=(ImageView) this.findViewById(R.id.gear);
		
		gear.setAnimation(rotateAnimation1);

		
		

		gear.setVisibility(View.VISIBLE);
		
		
		
		edi=(EditText) this.findViewById(R.id.editText1);
		
		
		extra=getIntent().getExtras();
		
		
		OnFocusChangeListener ofcListener = new MyFocusChangeListener();
		
		edi.setOnFocusChangeListener(ofcListener);
		
		
		
		
sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
		
		
		
		
		sharedpreferences.getString("nickKey", "No name defined");
		
		
		username=sharedpreferences.getString("nickKey", "No name defined");
		
		
		
		
		but.setOnClickListener(new OnClickListener()
		{
			
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				submiting=true;

				
			//	postData("{\"nid\":\"1\",\"cmts\":\"first article third comment\"}");
		
				
				Toast.makeText(con, "sending... "+username, Toast.LENGTH_SHORT).show();
				
				
				id=extra.getString("id");
				
			//	Toast.makeText(con, id, Toast.LENGTH_LONG).show();
				
				new clickPost().execute(id,username,edi.getText().toString());
				
				new DownloadJSON().execute();
				
				
				edi.clearComposingText();
				
				edi.clearFocus();
				
				
				
			}
			
		});
		
		
		
		
		
		con=this;
	
		
		
		arraylist = new ArrayList<HashMap<String, String>>();
		
		
		
	//	listview = (ListView) findViewById(R.id.listview);
		
		adapter = new CommentAdapter(CommentActivity.this, arraylist);
		
		 // mList = (JazzyListView) findViewById(R.id.listview);
		   
		mList = (ListView) findViewById(R.id.listView1);
		  
		  
		// Execute DownloadJSON AsyncTask
	//	new DownloadJSON().execute();
		
		
			
		
		
		
		
		
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
       
		swipeLayout.setOnRefreshListener(this);
        
        
         
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright, 
                android.R.color.holo_green_light, 
                android.R.color.holo_orange_light, 
                android.R.color.holo_red_light);
        
        
		
	}
	
	
	boolean submiting=false;
	
	
	
	

	// DownloadJSON AsyncTask
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
		
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
			mProgressDialog = new ProgressDialog(CommentActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Getting Comments..");
			// Set progressdialog message                                                                                                                        
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
		//	mProgressDialog.show();
			
		
		
		if(spinner!=null)
		{
			spinner.setVisibility(View.VISIBLE);
		
			swipeLayout.setRefreshing(true);
			  
		}
			
			 
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<HashMap<String, String>>();
		
			piclist = new ArrayList<HashMap<String, String>>();
			
			String listno=""+1;

			
			Bundle extra;
			
			extra=getIntent().getExtras();
			
			listno=extra.getString("id"); 
			
			{
				
		
		jsonobject = JSONfunctions.getJSONfromURL("http://noopus.net/noopazine/commentschat.php?data="+listno+"&cmno="+cmno);
	
		
			
			
		Log.d("LOGEE", ""+jsonobject);
			
			try {
				
				
				
				jsonarray = jsonobject.getJSONArray("noopazine");

					for (int i = jsonarray.length()-1; i > -1; i--) {

						map = new HashMap<String, String>();
						
			//			HashMap<String,String> picmap = new HashMap<String, String>();
						
						
						if(isConnectedToInternet())
					jsonobject = jsonarray.getJSONObject(i);
	
						
					if(map.get("cmts")!=jsonobject.getString("cmts"))
						map.put("cmts", jsonobject.getString("cmts").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
				
					
					if(map.get("username")!=jsonobject.getString("username"))
						map.put("username", jsonobject.getString("username").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
				
					
			//		if(map.get("nid")!=jsonobject.getString("nid"))
			//			map.put("nid", jsonobject.getString("nid").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
			
					
	
				
					arraylist.add(map);
					
					adapter = new CommentAdapter(CommentActivity.this, arraylist);
					
					
					
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			
			
	//		index = mList.getFirstVisiblePosition();
	//		v = mList.getChildAt(0);
	//		top = (v == null) ? 0 : v.getTop();

			
			}
				
			return null;
		}
		
		
		int index,top;
		
		View v;
		
		HashMap<String, String> map;
		
		
		
		
		
		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_Comment.xml
		//	listview = (ListView) findViewById(R.id.listview);
			
			if(spinner!=null)
			{
				spinner.setVisibility(View.GONE);
			
				swipeLayout.setRefreshing(false);
				  
			}
			
			
			
			gear.clearAnimation();
			
			
Animation fade = AnimationUtils.loadAnimation(con, R.anim.abc_fade_out);
            
if(gear.getVisibility()!=View.GONE)
            gear.startAnimation(fade);
            
            
			gear.setVisibility(View.GONE);
            
			
			
			
			
			
			mList.post(new Runnable() {
		        @Override
		        public void run() {
		        	
		        
		        	if(submiting)
		        	mList.setSelection(mList.getCount());
		        	else
		        		mList.setSelection(1);
			        		
		        		
		        }
		    });
			
			
			if(isConnectedToInternet())
			{		
				
		//		adapter = new CommentAdapter(CommentActivity.this, arraylist,piclist);
			// Set the adapter to the ListView
			
		
		
				adapter.notifyDataSetChanged();
				
				if(adapter!=null&&mList!=null)
			mList.setAdapter(adapter);
			
				
			
	//		mList.setSelectionFromTop(index, top);
			}
		 
		}
		
		
		
		  
		  
		  
		  
		  
	}
	
	
	
	
	
	
	
	public void postData(String nid) {
        // Create a new HttpClient and Post Headeråå
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://noopus.net/noopazine/cmtinsert.php");

     try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
           
            nameValuePairs.add(new BasicNameValuePair("myHttpData",nid));
            
           // nameValuePairs.add(new BasicNameValuePair("cmts",cmt));
            
            
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
	
	
	Timer autoUpdate;
	
	
	boolean exit=false;
	
	@Override
    public void onResume() {
      super.onResume();
      autoUpdate = new Timer();
    
      
      
     
	

      if(this.isConnectedToInternet()) 
      {
    	  new DownloadJSON().execute();  }
      else
      {
    	  
    	  
    	  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
  				con);
    	  
    	 
			
			
  			// set title
  			alertDialogBuilder.setTitle("This App needs Internet connection");
   
  			
  			 
  			
  			
  			// set dialog message
  			alertDialogBuilder
				.setMessage("This App needs Internet connection").setCancelable(false).setPositiveButton("Settings",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						
						if(!isConnectedToInternet())
						startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
						else
						dialog.cancel();
						
						
  						
					}
				  
				
				})
  				.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
  					public void onClick(DialogInterface dialog,int id) {
  						// if this button is clicked, just close
  						// the dialog box and do nothing
  						
  						exit=true;
  						
  						CommentActivity.this.finish();
  						
  						dialog.dismiss();
  						
  						
  					
  					}
  				}).setNeutralButton("Retry",new DialogInterface.OnClickListener() {
  					public void onClick(DialogInterface dialog,int id) {
  						// if this button is clicked, just close
  						// the dialog box and do nothing
  						
  						if(isConnectedToInternet())
  						dialog.cancel();

  					}
  				});;
   
  				
  				
  				
  				
  				
  			
  				
  				// create alert dialog
  				final AlertDialog alertDialog = alertDialogBuilder.create();
   
  				
  				
  				// show it
  				
  				
  				 autoUpdate.schedule(new TimerTask() {
  			         @Override
  			         public void run() {
  			           runOnUiThread(new Runnable() {
  			             public void run() {
  			     	//		if(isConnectedToInternet())     				
  			         //      new DownloadJSON().execute(); // this is the class that downloads the data from the server.
  			           
  			            	if(!isConnectedToInternet()&&exit==false)     				
  			  			    	alertDialog.show();
  			  			             
  			             }
  			           });
  			         }
  			       }, 0, 5000); // updates each 10 secs
  			     
  				 
  				 
  			}

    
	}

	
		

    @Override
    public void onPause() {
       autoUpdate.cancel();
       super.onPause();
    }

    
    
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
    
    
    
    
    
    
    
    
    public class clickPost extends AsyncTask<String, String, String> {
        
    	
    	
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
		
    	if(spinner!=null)
            spinner.setVisibility(View.VISIBLE);
    		
    	}
    	
    	
    	
    	
    	
    	@Override
        protected String doInBackground(String... params) {
           
            String str = "";
           // Create a new HttpClient and Post Header
            post(params[0],params[1],params[2]);
            
            
            
            
			return str;  
      
        }

        /**
         * on getting result
         */
        @Override
        protected void onPostExecute(String result) {
            // something with data retrieved from server in doInBackground
        
            spinner.setVisibility(View.GONE);
            
            gear.clearAnimation();
			
            
            Animation fade = AnimationUtils.loadAnimation(con, R.anim.abc_fade_out);
            
            if(gear.getVisibility()!=View.GONE)
            gear.startAnimation(fade);
            
            gear.setVisibility(View.GONE);
            
            
            
        	
        
        }
        
        
        public void post(String nid,String username,String cmts)
        {
        	 byte[] result = null;
        	 
        	    String str = "";
        	    
        	 HttpClient httpclient = new DefaultHttpClient();
        	  
             HttpPost httppost = new HttpPost("http://noopus.net/noopazine/cmtinsert.php");
  
             
                    
             try {
                     // Add your data
                     List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                     nameValuePairs.add(new BasicNameValuePair("nid", nid));
                     nameValuePairs.add(new BasicNameValuePair("cmts", cmts));
                     nameValuePairs.add(new BasicNameValuePair("username", username));
                     
                     
                     httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                     // Execute HTTP Post Request
                     HttpResponse response = httpclient.execute(httppost);
                     StatusLine statusLine = response.getStatusLine();
                     if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
                     result = EntityUtils.toByteArray(response.getEntity());
                     str = new String(result, "UTF-8");
                 }
               } catch (ClientProtocolException e) {
                 // TODO Auto-generated catch block
             } catch (IOException e) {
                 // TODO Auto-generated catch block
             }
        }
        
    }
    
   
    private class MyFocusChangeListener implements OnFocusChangeListener {

        public void onFocusChange(View v, boolean hasFocus){

            if(v.getId() == R.id.editText1 && !hasFocus) {

                InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }
    }







	@Override
	public void RibbonMenuItemClick(int itemId) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

new Handler().postDelayed(new Runnable() {
			
			
        	
            @Override public void run() {
            	
            	cmno+=5;
            	
            	   if(isConnectedToInternet()) 
            	    	  new DownloadJSON().execute(); // this is the class that downloads the data from the server.
            	   
            	   
            }
        }, 1000);
		
	
	}
    
    
}