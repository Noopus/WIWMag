package com.androidbegin.jsonparsetutorial;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

public class URLImageParser implements ImageGetter {
Context c;
TextView container;

/***
 * Construct the URLImageParser which will execute AsyncTask and refresh the container
 * @param t
 * @param c
 */

int wid,hei;

public URLImageParser(TextView t, Context c) {
    this.c = c;
    this.container = t;
    
    wid=container.getWidth();
    hei=container.getHeight();
}



public Drawable getDrawable(String source) {

	
	URLDrawable urlDrawable = new URLDrawable();

    // get the actual source
    ImageGetterAsyncTask asyncTask = 
        new ImageGetterAsyncTask( urlDrawable);

    
    asyncTask.execute(source);

    
    // return reference to URLDrawable where I will change with actual image from
    // the src tag
    
    return urlDrawable;

}




public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable>  {
    URLDrawable urlDrawable;

    
    public ImageGetterAsyncTask(URLDrawable d) {
        this.urlDrawable = d;
    }

    @Override
    protected Drawable doInBackground(String... params) {
        String source = params[0];
        return fetchDrawable(source);
    }

    @Override 
    protected void onPostExecute(Drawable result) { 
        // set the correct bound according to the result from HTTP call 
        
        // change the reference of the current drawable to the result 
        // from the HTTP call 
      
    	
    	if(result!=null)

    	{		urlDrawable.drawable = result; 

        
       urlDrawable.setBounds(
    		   0, 0, 0+result.getIntrinsicWidth()
    		   , 0+result.getIntrinsicHeight()+result.getIntrinsicHeight()/2);  

    	
       
        // redraw the image by invalidating the container 
        URLImageParser.this.container.invalidate();

        // For ICS
        URLImageParser.this.container.setHeight((URLImageParser.this.container.getHeight() 
        + (int)(result.getIntrinsicHeight()*1.5f)));

        // Pre ICS
        URLImageParser.this.container.setEllipsize(null);
        
    	}
    	
    	
    	
    } 
    /***
     * Get the Drawable from URL
     * @param urlString
     * @return
     */
    public Drawable fetchDrawable(String urlString) {
        try {
            URL aURL = new URL(urlString);
            final URLConnection conn = aURL.openConnection(); 
            conn.connect(); 
            final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream()); 
            final Bitmap bm = BitmapFactory.decodeStream(bis);

            
            wid=container.getWidth();

            hei=container.getHeight();

            int nh;
            
            if(wid<bm.getWidth())
		    nh = (int) ( bm.getHeight() * ( bm.getWidth()/wid) );
            else
            	nh = (int) ( bm.getHeight() * (wid / bm.getWidth()) );
            	
            	
		    Bitmap scaled = Bitmap.createScaledBitmap(bm, wid, nh, true);  

            Drawable drawable = new BitmapDrawable(scaled);
            

         
      
            
            
            
            drawable.setBounds(0,0,container.getWidth(),(int)(nh*0.8f));
  
            return drawable;
        
        } catch (Exception e) {
        
        	return null;
        
        }
        
        
    }
}

}