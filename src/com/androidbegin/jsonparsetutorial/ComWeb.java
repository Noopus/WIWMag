package com.androidbegin.jsonparsetutorial;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;


public class ComWeb extends Activity {
	
ImageView gv;
Button bt;

WebView wv;


Intent go;



boolean loadingFinished = true;
boolean redirect = false;


Animation fade;


String id;

Bundle extra;



RotateAnimation rotateAnimation1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Get the view from listview_main.xml
		setContentView(R.layout.comweb);
		
	wv=(WebView)findViewById(R.id.web);
	
	
	gv=(ImageView)findViewById(R.id.gear);

	
	
rotateAnimation1 = new RotateAnimation(0, 360,
	        Animation.RELATIVE_TO_SELF, 0.5f,
	        Animation.RELATIVE_TO_SELF, 0.5f);
	rotateAnimation1.setInterpolator(new LinearInterpolator());
	rotateAnimation1.setDuration(2000);
	rotateAnimation1.setRepeatCount(2000);
	
	
	
	fade = AnimationUtils.loadAnimation(this, R.anim.abc_fade_out);

	
	
	
	gv.setAnimation(rotateAnimation1);


	gv.setVisibility(View.VISIBLE);
	
	
	  WebSettings webSettings = wv.getSettings();
	    webSettings.setJavaScriptEnabled(true);
	    webSettings.setDomStorageEnabled(true);
	    wv.getSettings().setPluginState(PluginState.ON);
	    wv.getSettings().setDomStorageEnabled(true);
	 
	    
	    
		
		extra=getIntent().getExtras();
		
		id=extra.getString("id");

	
	wv.loadUrl("http://whatyouwantmagazine.com/mobilefacebookcmt.php?nid="+id);

	


	
	wv.setWebViewClient(new WebViewClient() {

	   @Override
	   public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
	       if (!loadingFinished) {
	          redirect = true;
	       }

	        gv.setVisibility(View.VISIBLE);
	         
	        gv.setAnimation(rotateAnimation1);

	        
	   loadingFinished = false;
	   view.loadUrl(urlNewString);
	   return true;
	   }

	   @Override
	   public void onPageStarted(WebView view, String url, Bitmap facIcon) {
	        loadingFinished = false;
	        //SHOW LOADING IF IT ISNT ALREADY VISIBLE  
	   
	      
	        gv.setVisibility(View.VISIBLE);
	         
	        gv.setAnimation(rotateAnimation1);
	    	
	   }

	   @Override
	   public void onPageFinished(WebView view, String url) {
	       if(!redirect){
	          loadingFinished = true;
	          
	         
	         gv.setAnimation(fade);
		        
		        gv.setVisibility(View.GONE);
	          
	       }

	       if(loadingFinished && !redirect){
	         //HIDE LOADING IT HAS FINISHED
	       } else{
	          redirect = false; 
	       }

	    }
	});
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
	
		
		extra=getIntent().getExtras();
		
		id=extra.getString("id");

	    gv.setAnimation(rotateAnimation1);
	    
	    
		wv.loadUrl("http://whatyouwantmagazine.com/mobilefacebookcmt.php?nid="+id);
		
		
	}
	
	
	
	@Override
	public void onBackPressed()
	{
		
		Intent i=new Intent(ComWeb.this,MainActivity.class);
		
	    startActivity(i);
	    
	    finish();
		
	}
	
	
	
	 public void back(View v){
			
			
			if(wv.canGoBack()){
				
				wv.goBack();
			}else
			onBackPressed();
		
		}
	
	 
	 
	 public void refresh(View v){
			
			
	        gv.setVisibility(View.VISIBLE);
	         
	        gv.setAnimation(rotateAnimation1);
	
	    	extra=getIntent().getExtras();
			
			id=extra.getString("id");

		    this.loadingFinished=false;
		    
		    
		    this.redirect=false;
		    
			wv.loadUrl("http://whatyouwantmagazine.com/mobilefacebookcmt.php?nid="+id);
			
		
	 }

}