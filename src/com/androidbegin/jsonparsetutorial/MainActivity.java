package com.androidbegin.jsonparsetutorial;

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




































import com.darvds.ribbonmenu.RibbonMenuView;
import com.darvds.ribbonmenu.iRibbonMenuCallback;
import com.etsy.android.grid.StaggeredGridView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;


@SuppressLint("NewApi")
public class MainActivity extends Activity  implements SwipeRefreshLayout.OnRefreshListener,iRibbonMenuCallback{
	// Declare Variables
	JSONObject jsonobject;
	JSONArray jsonarray;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
	String buff,b2;
	
	ImageView mimg;
	
	
	 SharedPreferences sharedpreferences;
		
	
	
	ArrayList<HashMap<String, String>> piclist;
	
	
	static String RANK = "date";
	static String COUNTRY = "title";
	static String POPULATION = "content";
	static String FLAG = "image";

	static String LISTNUM = "listno"; 
    
	
	
	//static String COMMENTS = "comments";
	
	
	
	
	Button more;
    
    private static final String KEY_TRANSITION_EFFECT = "transition_effect";

   // private JazzyListView mList;
    
    private StaggeredGridView mList;
    
    private HashMap<String, Integer> mEffectMap;
   // private int mCurrentTransitionEffect = JazzyHelper.SLIDE_IN;

    
    Button but;
    
    boolean click=false;
    
    Context con;
	
    
    int nwno=6;
    
    
    SwipeRefreshLayout swipeLayout;
    
    JSONArray contacts = null;

    ArrayList<String> carray;
	
	JSONObject jsob;
	
	
    ArrayList<HashMap<String, String>> contactList;

	

    TextView header;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
		setContentView(R.layout.listview_main);
		
		
	
		con=this;
	
		
		
		
		arraylist = new ArrayList<HashMap<String, String>>();
		
		piclist = new ArrayList<HashMap<String, String>>();
		
		
	//	listview = (ListView) findViewById(R.id.listview);
		
		adapter = new ListViewAdapter(MainActivity.this, arraylist,piclist);
		
		
		 // mList = (JazzyListView) findViewById(R.id.listview);
		 
		
		contactList = new ArrayList<HashMap<String, String>>();

		
		mList = (com.etsy.android.grid.StaggeredGridView) findViewById(R.id.listview);
		  
		  
		// Execute DownloadJSON AsyncTask
	//	new DownloadJSON().execute();
		
		

		ImageView mimg=(ImageView)findViewById(R.id.flag);
		
		header=(TextView)findViewById(R.id.header);
		
		
		more=(Button)findViewById(R.id.more);
		
		more.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nwno+=5;
				 if(isConnectedToInternet()) 
			    	  new DownloadJSON().execute(); // this is the class that downloads the data from the server.
			    
			}
			
		});
		
		Button btn=(Button)findViewById(R.id.optionbutton);
		
		 if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH){
				
	    		
	     	    
				final PopupMenu popupMenu = new PopupMenu(this, btn); 

				popupMenu.inflate(R.menu.optins);
	         
	         btn.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {
		                popupMenu.show();
		            }
		        });
	    
	         popupMenu.setOnMenuItemClickListener(
		                new PopupMenu.OnMenuItemClickListener() {
		            @Override
		            public boolean onMenuItemClick(MenuItem item) {
		                switch (item.getItemId()) {
		                    case R.id.menu_home:
		                    {
		                    	
		                    	  header.setText(Html.fromHtml("Recent"));
		                    		
		                    	  
		                    	if(isConnectedToInternet())
		          				{
		                    	
		                    		
		                    	  home=true;
		                            
		                            if(isConnectedToInternet()) 
		                          	  new DownloadJSON().execute(); // this is the class that downloads the data from the server.
		                       
		                    	}
		                    }
		                    
		                    break;
		                    
		                    
		                    case R.id.menu_sub:
		                    {
		                   
		             //       	 Intent in=new Intent(con,submitcontents.class);
		 						  
		   			  //          con.startActivity(in);
		   		        
		                    	 Intent in=new Intent(MainActivity.this,ArticleSubmit.class);
		               		  
		                         startActivity(in);

		   			            
		                    }
		                    break;
		          
		                    
		                    case R.id.menu_photo:
		                    {
		                   
		             //       	 Intent in=new Intent(con,submitcontents.class);
		 						  
		   			  //          con.startActivity(in);
		   		        
		                    	 Intent in=new Intent(MainActivity.this,Submitphoto.class);
		               		  
		                         startActivity(in);

		   			            
		                    }
		                    break;
		          
		                    
		                    
		                   case R.id.menu_exit:
		                    {
		                    	
		                    	finish();
		                    	
		                    }
		                    
		                    break;
		                    
			                    
		                }	
			                return true;
			            }
			        });
				
		}else{
			
		
		btn.setOnClickListener(new OnClickListener() {

	        public void onClick(View v) {
	            openOptionsMenu();
	        }
	    });
	  
		}
		
		
	  
	
		
		 if(isConnectedToInternet()) 
	    	  new GetContacts().execute(); // this is the class that downloads the data from the server.

		
		carray=new ArrayList<String>();
		
		
		 rbmView = (RibbonMenuView) findViewById(R.id.ribbonMenuView1);
	     
		 
		 rbmView.setMenuClickCallback(this);
	
		 
		   Button rib=(Button)this.findViewById(R.id.menubutton);
	         
	         rib.setOnClickListener(
	        		 new OnClickListener(){

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

						     rbmView.toggleMenu();
													
						}
	        	 
	         });
	    		
		 
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int h = displaymetrics.heightPixels;
		int w = displaymetrics.widthPixels;
		
		
		
	//	android.view.ViewGroup.LayoutParams layoutParams = mimg.getLayoutParams();
	//	layoutParams.width = w/2-28;
	//	layoutParams.height =w/2-28;
	//	mimg.setAdjustViewBounds(true);
		
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        
        
         
        swipeLayout.setColorScheme(  android.R.color.holo_blue_light, 
                android.R.color.holo_green_light, 
                android.R.color.holo_purple, 
                android.R.color.holo_red_light);
	}

	
	
	
	
	
	
	
	
	String curcat;
	
	
	
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			
			
			
		//	 frameAnimation.setAlpha(1);
				
			 
		//	frameAnimation.setVisible(true, true);
			

		}  

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
		

		//	shit="gooo";
			
	//		if (jsonStr != null) 
			
			jsob = JSONfunctions.getJSONfromURL("http://whatyouwantmagazine.com/category.php");
	
			
				try {
					
					// Getting JSON Array node
					contacts = jsob.getJSONArray("cat");

					
					
					
					
					// looping through All Contacts
					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);
						
					//	String cate = jsonobject.getString("name").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'");
			
						// Phone node is JSON Object
					//	JSONObject phone = c.getJSONObject(TAG_PHONE);
									
						String cate = c.getString("category").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'");
					
						
						// adding each child node to HashMap key => value
						
						
						contact.put("category", cate);
						

						// adding contact to contact list
						
						contactList.add(contact);   
					
		
						
					//contact.put("category", cate);

					
					
					// adding contact to contact list
		//			contactList.add(contact);
					
					
		//			carray.add(contact.get("category"));
			
						
					carray.add("Recent");	
					
					carray.add("Stories");	
					
					carray.add("Startup");	
					
					carray.add("Nerdvana");	
					
					carray.add("Fotography");	
					
					carray.add("Girlthing");	
					
					carray.add("Entertainment");	
					
						
					shit=carray.get(0);
					
					   size=carray.size();
				
					   
					}
					
				//	shit="jljlkjlj";
				} catch (JSONException e) {
					e.printStackTrace();
				}


			return null;
		}

		
		
		
		
String shit;
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
	//		if (pDialog.isShowing())
			 super.onPostExecute(result);
			 
			 
		//	 frameAnimation.setVisible(false, false);
				
		//	 frameAnimation.setAlpha(0);
			 
	//		Toast.makeText(MainActivity.this, shit, Toast.LENGTH_LONG).show();
		
			rbmView.setMenuItems(carray);
	        
		}

	}
	
	
	
	int size;
	
	
	private RibbonMenuView rbmView;
	
	HashMap<String, String> contact = new HashMap<String, String>();
  
	boolean home=true;
	
	// DownloadJSON AsyncTask
	@SuppressLint("NewApi")
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
			
			swipeLayout.setRefreshing(true);
	         
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<HashMap<String, String>>();
		
			piclist = new ArrayList<HashMap<String, String>>();
			
			
			// Retrieve JSON Objects from the given URL address
//			jsonobject = JSONfunctions
//					.getJSONfromURL("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");

		//	http://192.168.1.68/myjson/test.json
	
			
		//	jsonobject = JSONfunctions.getJSONfromURL("http://192.168.1.68/myjson/count.json");

			

			  
			  
			
			if(isConnectedToInternet())
			{
				
		
	//	jsonobject = JSONfunctions.getJSONfromURL("http://noopus.net/jasontest/count.json");
		
	/*			
			if(carray.get(curpos)==null)
			{
				carray.set(curpos, "");
			}
		*/
				
				
			if(!home&&!carray.get(curpos).equals("Recent"))
			{
				jsonobject = JSONfunctions.getJSONfromURL("http://whatyouwantmagazine.com/chat.php?category="+carray.get(curpos)+"&nwno="+nwno);
				
		//		jsonobject = JSONfunctions.getJSONfromURL("http://whatyouwantmagazine.com/chat.php?nwno=1");
					
			}
			else
				jsonobject = JSONfunctions.getJSONfromURL("http://whatyouwantmagazine.com/chat.php?nwno="+nwno+"&category=recent");
				
				

			
		Log.d("LOGEE", ""+jsonobject);
			
	//		noopus.net/jasontest/count.json
			
			try {
				// Locate the array name in JSON
				jsonarray = jsonobject.getJSONArray("noopazine");

			//	for (int i = jsonarray.length()-3; i < jsonarray.length(); i++) {
				
					for (int i = 0; i < jsonarray.length(); i++) {

						map = new HashMap<String, String>();
						
						HashMap<String,String> picmap = new HashMap<String, String>();
						
						
						if(isConnectedToInternet())
					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
				
					
					if(map.get("date")!=jsonobject.getString("date"))
						map.put("date", jsonobject.getString("date").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
					
					
					
					
					if(map.get("title")!=jsonobject.getString("title"))
						map.put("title", jsonobject.getString("title").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
					
					if(map.get("content")!=jsonobject.getString("content"))
					{

						try {
						String	s = new String(jsonobject.getString("content").getBytes(), "UTF-8").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'");
						
				
						//	map.put("content", jsonobject.getString("content").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
						
						map.put("content", s);
						
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
			
					
			//		map.put("content", jsonobject.getString("content").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
					
		//			
				
		//			map.put("listno", ""+adapter.listno);
					
			
					if(map.get("image")!=jsonobject.getString("image"))
						map.put("image", jsonobject.getString("image"));
				
					
					if(map.get("id")!=jsonobject.getString("id"))
						map.put("id", jsonobject.getString("id"));
				
					
					if(map.get("tags")!=jsonobject.getString("tags"))
						map.put("tags", jsonobject.getString("tags"));
				
					
					if(map.get("author")!=jsonobject.getString("author"))
						map.put("author", jsonobject.getString("author").replaceAll("\\\\\"", "\"").replaceAll("\\\\\'", "\'"));
				
					if(map.get("link")!=jsonobject.getString("link"))
						map.put("link", jsonobject.getString("link").replaceAll("\\\\\'", "\'"));
				
					
					
					if(map.get("category")!=jsonobject.getString("category"))
					{
						map.put("category", jsonobject.getString("category"));
					
						curcat=jsonobject.getString("category");
						
					}
				
				
					picmap.put("image", jsonobject.getString("image"));
					
					
		//			if(click)
				
				
					if(!click)
					piclist.add(picmap);
					
					// Set the JSON Objects into the array
				
					arraylist.add(map);
					
					
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			
			
		//	index = mList.getFirstPosition();
		//	v = mList.getChildAt(0);
		//	top = (v == null) ? 0 : v.getTop();

			
			}
			// ...

			// restore
		
			
			
			
			return null;
		}
		int index,top;
		View v;
		
		HashMap<String, String> map;
		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml
		//	listview = (ListView) findViewById(R.id.listview);
			
			     
			
			
			
		     swipeLayout.setRefreshing(false);
	           
			
			
			if(isConnectedToInternet())
			{		
				
				adapter = new ListViewAdapter(MainActivity.this, arraylist,piclist);
			// Set the adapter to the ListView
			
		
				
			
			
			
		
				adapter.notifyDataSetChanged();
				
				if(mList!=null)
			mList.setAdapter(adapter);
			
			
		//	mList.setSelectionFromTop(index, top);
			
	//		mList.setSelectionToTop();
			}
			// Close the progressdialog
		//	mProgressDialog.dismiss();
		}
	}
	
	
	
	Timer autoUpdate;
	
	
	boolean exit=false;
	
	@Override
    public void onResume() {
      super.onResume();
      autoUpdate = new Timer();
    
      
      
     
	

      if(this.isConnectedToInternet()) 
    	  new DownloadJSON().execute(); // this is the class that downloads the data from the server.
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
  						
  						MainActivity.this.finish();
  						
  						dialog.dismiss();
  						
  						
  					
  					}
  				}).setNeutralButton("Retry",new DialogInterface.OnClickListener() {
  					public void onClick(DialogInterface dialog,int id) {
  						// if this button is clicked, just close
  						// the dialog box and do nothing
  						
  						if(isConnectedToInternet())
  						{
  							
  							 new DownloadJSON().execute(); // this is the class that downloads the data from the server.
  	  			           
  							dialog.cancel();
  						
  						}

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
	public void onBackPressed()
	{
		
		 AlertDialog.Builder alertexit = new AlertDialog.Builder(
 				con);
 	  
 	  
 	  alertexit.setTitle("are you sure you want to exit ?");
		  

				
		
		
			alertexit
		.setMessage("Do you want to exit ?").setCancelable(false).setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, close
				// current activity
				
				
				MainActivity.this.finish();
					
			}
		  
		
		})
			.setNegativeButton("No",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, just close
					// the dialog box and do nothing
					
					dialog.dismiss();
				}
			});
			
			
			
			final AlertDialog alertex = alertexit.create();
			
			
		  alertex.show();
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


	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			
			
        	
            @Override public void run() {
            	
            	nwno=10;
            	
            	   if(isConnectedToInternet()) 
            	    	  new DownloadJSON().execute(); // this is the class that downloads the data from the server.
            	   
            	   
            }
        }, 1000);
		
		
	}

	
	int curpos;
	

	@Override
	public void RibbonMenuItemClick(int itemId) {
		// TODO Auto-generated method stub
		
		
		home=false;
		
		curpos=itemId;
		
		
		
        header.setText(Html.fromHtml(carray.get(curpos)));
		
		new DownloadJSON().execute();
	
	
	}
    
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.optins, menu);
	    return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		  case R.id.menu_home:
          {
          	
        	  home=true;
              
        	  
        	  header.setText(Html.fromHtml("Recent"));
      		
        	   
              if(isConnectedToInternet()) 
            	  new DownloadJSON().execute(); // this is the class that downloads the data from the server.
        
          }
          
          break;
          
		  
          
		  case R.id.menu_sub:
          {
          	
        	  
        	  Intent in=new Intent(con,ArticleSubmit.class);
				  
		            con.startActivity(in);
	           
          	
          } 
          
          break;
		
          
		  case R.id.menu_photo:
          {
         
   //       	 Intent in=new Intent(con,submitcontents.class);
				  
		  //          con.startActivity(in);
	        
         	 Intent in=new Intent(MainActivity.this,Submitphoto.class);
      		  
             startActivity(in);

	 
		            
          }
          break;

          
          
          case R.id.menu_exit:
          {
          	
          	finish();
          	
          }
          
          break;
          
              
      }
		return true;	
	}
	
	
   
 
    
    
}