package com.androidbegin.jsonparsetutorial;

import java.util.ArrayList;
import java.util.HashMap;

import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import com.squareup.picasso.Picasso;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class photography extends Activity {
	
	String rank;
	String country;
	String population;
	String flag;
	String id;	
	String position;
    String comments;
    
 ParallaxScrollView sv;
    
    String nid;
    

	
	Button submit;
	
		
	Coustxt comment;
	
	Context con;
	
	Intent i;
	
	
	Bundle extra,commentextra;
	
	String[] has=new String[10];
	
	
	
    
ArrayList<HashMap<String, String>> arraylist;
	
	ArrayList<HashMap<String, String>> piclist;
	
	
	static String RANK = "date";
	static String COUNTRY = "title";
	static String POPULATION = "content";
	static String FLAG = "image";

	static String COMMENTS = "comments";
	
	String listno ;
	
	
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Get the view from listview_main.xml
		setContentView(R.layout.photography);
		
		ImageView iv=(ImageView)findViewById(R.id.ImgView);
		
		
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
		
		comments=i.getStringExtra("comments");
	
		id=i.getStringExtra("id");
		
		
		nid=i.getStringExtra("nid");
		
	listno = extra.getString("listno");
	
	
ImageView imgflag = (ImageView) findViewById(R.id.ImgView);
	
	try
	{
		Picasso.with(this).load(flag).into(imgflag);
	}
	catch(IllegalArgumentException e){
		
		}

	
	TextView destxt=(TextView)findViewById(R.id.destxt);
	 
	 
	 destxt.setText(Html.fromHtml(population));
	 
	 
	 submit=(Button)findViewById(R.id.commbut);
	 
	 
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
		
		
	
	 LinearLayout layout = (LinearLayout)findViewById(R.id.baseline);
		// Gets the layout params that will allow you to resize the layout
		android.view.ViewGroup.LayoutParams params = layout.getLayoutParams();
		
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
			
			
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;
			
			
			android.view.ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
			layoutParams.width = width;
			layoutParams.height =2*height/3;
		iv.setLayoutParams(layoutParams);
	//	params.height = (height/3)-55;
		
			
	}else{
		
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		
		android.view.ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
		layoutParams.width = width;
		layoutParams.height = 2*height/3;
	iv.setLayoutParams(layoutParams);
	          

	//params.height = (height/3)-55;
	
	}
		
		

	// Changes the height and width to the specified *pixels*

		
		
		
	}
	
	
	
	public void back(View v){
		
		
		
		onBackPressed();
	
	}
	
	
public void combut(View viw){
		
		
		////////    code for comment implementation     ///////
	  Intent in=new Intent(viw.getContext(),CommentActivity.class);
		
	  
	  in.putExtra("id", id);
		
        
        startActivity(in);
  
	
	}
	
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	   
	}
	
	
	
	

}
