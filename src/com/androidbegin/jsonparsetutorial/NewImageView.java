package com.androidbegin.jsonparsetutorial;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class NewImageView extends ImageView {

    public NewImageView(Context context) {
        super(context);
    }

    public NewImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        
        int height=0;
        
        if(getDrawable()!=null)
        height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        
        setMeasuredDimension(width, height);
    }
}