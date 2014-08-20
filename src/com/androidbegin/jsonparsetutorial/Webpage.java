package com.androidbegin.jsonparsetutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Webpage extends Activity {

	
	
	WebView web;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.web);

		web=(WebView) this.findViewById(R.id.webView1);
		/*
		web.setWebViewClient(new WebViewClient(){
			
			
			
			
			
			 
			
			@Override
	        public void onReceivedError(WebView view, int errorCode,
	                String description, String failingUrl) {
	            // Handle the error
	        }

	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	        }

			
			
			
		});
		*/
		
	}

@Override
public void onResume()
{
	 super.onResume();

	 Intent i=getIntent();
	 
	 
	 web.loadUrl(i.getStringExtra("link"));
	 
	 
	
}


}
