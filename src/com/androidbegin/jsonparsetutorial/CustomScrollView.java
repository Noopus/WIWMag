package com.androidbegin.jsonparsetutorial;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
	private float xDistance, yDistance, lastX, lastY;

	public CustomScrollView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
	    switch (ev.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            xDistance = yDistance = 0f;
	            lastX = ev.getX();
	            lastY = ev.getY();
	            break;
	        case MotionEvent.ACTION_MOVE:
	            final float curX = ev.getX();
	            final float curY = ev.getY();
	            xDistance += Math.abs(curX - lastX);
	            yDistance += Math.abs(curY - lastY);
	            lastX = curX;
	            lastY = curY;
	            if(xDistance > yDistance)
	                return false;
	    }

	    return super.onInterceptTouchEvent(ev);
	}
}