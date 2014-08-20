package com.androidbegin.jsonparsetutorial;





import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class SignUp extends Activity {
	
	
	public static final String MyPREFERENCES = "MyPrefs" ;
	
	EditText ed;
	  
	   public static final String Email = "emailKey"; 
	   public static final String nickName = "nickKey"; 
	  
	   SharedPreferences sharedpreferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Get the view from listview_main.xml
		setContentView(R.layout.signup);
		
		
		 sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	


		 String nickname=sharedpreferences.getString(nickName, "").toString();

		   if(!nickname.matches("")){
			 
			 Intent i = new Intent(this.getApplicationContext(), Coverphoto.class);
				startActivity(i); 
			 
		 }
		 
		 
	ImageView iv=(ImageView)findViewById(R.id.imageView1);
	
	
	 ed=(EditText)findViewById(R.id.editText1);
	
	
	
	
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
			
			
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;
			
			
			android.view.ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
			layoutParams.width = height/3;
			layoutParams.height =height/3;
		iv.setLayoutParams(layoutParams);
			
	}else{
		
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int h = displaymetrics.heightPixels;
		int w = displaymetrics.widthPixels;
		
		android.view.ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
		layoutParams.width = h/3;
		layoutParams.height = h/3;
	iv.setLayoutParams(layoutParams);
	          

		
	}
		
	
	Button bt=(Button)findViewById(R.id.button1);	
	
	
		
	
	
	
	
	}
	
	
	
	public void signin(View vi){
		
		if(ed.getText().toString().matches("")){
			
			
			
			Toast.makeText(this.getApplicationContext(),"Please enter your Nickname", Toast.LENGTH_LONG).show();
			
			
		}else{
			
		
		
		Editor editor = sharedpreferences.edit();
				editor.putString(nickName, ed.getText().toString());
	      
	      editor.commit(); 
	      
	      
	      
	      
	      Intent i = new Intent(this.getApplicationContext(), Coverphoto.class);
			
	     
	      
	      startActivity(i); 
			
	      
	  //    finish();
	      
	      
			
			Toast.makeText(this.getApplicationContext(),"Welcome to WYW Magazine "+sharedpreferences.getString(nickName, "")+"!!!", Toast.LENGTH_LONG).show();
		
		}
	}
	
	
	@Override
	public void onStop()
	{
		super.onStop();
		
		finish();
		
	}

}
