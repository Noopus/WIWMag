package com.androidbegin.jsonparsetutorial;

import java.util.ArrayList;
import java.util.HashMap;


import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	
	
	
	HashMap<String, String> resultp = new HashMap<String, String>();

	HashMap<String, String> peepee = new HashMap<String, String>();

	
	boolean isload;
	
	
	
	
	
	public CommentAdapter(Context context,ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		
		  inflater = LayoutInflater.from(context);
	      
		data = arraylist;
	
		isload=false;
		
	}

	
	

	
	
	@Override
	public int getCount() 
	{
		return data.size();
	}

	
	@Override
	public Object getItem(int position) {
		return null;
	}

	
	@Override
	public long getItemId(int position) {
		return 0;
	}

	  ViewHolder holder;

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		
		
		
		
	        if (convertView == null) {
	            convertView = inflater.inflate(R.layout.commentlistitem, null);
	            holder = new ViewHolder(convertView);
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder) convertView.getTag();
	        }

		
	  
	        resultp = data.get(position);

	    
		
		holder.country.setText(resultp.get(CommentActivity.COMMENT));


		holder.user.setText(resultp.get(CommentActivity.USERNAME));

		
		
		
		
		
		return convertView;
	}
	
	
	static class ViewHolder {
      
		
		
		TextView user;
		
		Coustxt country;

		
		
		
		
        ViewHolder(View view) {
            country = (Coustxt) view.findViewById(R.id.country);
            
            user = (TextView) view.findViewById(R.id.user);
            
        }
    }
	
	
	
	
	
}
