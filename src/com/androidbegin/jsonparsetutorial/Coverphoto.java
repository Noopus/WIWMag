package com.androidbegin.jsonparsetutorial;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Coverphoto extends Activity implements OnClickListener {
	
ImageView iv,gv;
Button bt;
	


Intent go;

private GestureDetector gestureDetector;

View.OnTouchListener gestureListener;




	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Get the view from listview_main.xml
		setContentView(R.layout.coverphoto);
		
	iv=(ImageView)findViewById(R.id.imageView1);
	gv=(ImageView)findViewById(R.id.imageView2);
	
	
	Picasso.with(this).load("http://whatyouwantmagazine.com/cover.jpg").into(iv);
		
	RotateAnimation rotateAnimation1 = new RotateAnimation(0, 360,
	        Animation.RELATIVE_TO_SELF, 0.5f,
	        Animation.RELATIVE_TO_SELF, 0.5f);
	rotateAnimation1.setInterpolator(new LinearInterpolator());
	rotateAnimation1.setDuration(2000);
	rotateAnimation1.setRepeatCount(2000);
gv.startAnimation(rotateAnimation1);
	
	


gestureDetector = new GestureDetector(new SwipeGestureDetector());
gestureListener = new View.OnTouchListener() {
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
};


iv.setOnTouchListener(gestureListener);

	}
	
	
	
	
	public void go(View v){
		
		
		 Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
			startActivity(i); 
			finish();
		
	}

	
	
	private void onLeftSwipe() {
	    Toast t = Toast.makeText(Coverphoto.this, "Left swipe", Toast.LENGTH_LONG);
	    t.show();
	    go = new Intent("test.apps.FLORA");
	    startActivity(go);
	}

	private void onRightSwipe() {
	    Toast t = Toast.makeText(Coverphoto.this, "Right swipe", Toast.LENGTH_LONG);
	    t.show();
	    go = new Intent("test.apps.FAUNA");
	    startActivity(go);
	}
	
	
	
	private class SwipeGestureDetector extends SimpleOnGestureListener {
	    private static final int SWIPE_MIN_DISTANCE = 50;
	    private static final int SWIPE_MAX_OFF_PATH = 200;
	    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

	    @Override
	    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
	            float velocityY) {
	        try {
	            Toast t = Toast.makeText(Coverphoto.this, "Gesture detected", Toast.LENGTH_SHORT);
	            t.show();
	            float diffAbs = Math.abs(e1.getY() - e2.getY());
	            float diff = e1.getX() - e2.getX();

	            if (diffAbs > SWIPE_MAX_OFF_PATH)
	                return false;

	            // Left swipe
	            if (diff > SWIPE_MIN_DISTANCE
	                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	            	Coverphoto.this.onLeftSwipe();
	            } 
	            // Right swipe
	            else if (-diff > SWIPE_MIN_DISTANCE
	                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	            	Coverphoto.this.onRightSwipe();
	            }
	        } catch (Exception e) {
	            Log.e("Home", "Error on gestures");
	        }
	        return false;
	    }

	
}







	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}