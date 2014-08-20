package com.androidbegin.jsonparsetutorial;

import java.io.File;



import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Random;









import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Submitphoto extends Activity {
	
	
	protected static final int REQUEST_CODE_GET_CONTENT = 2;
	
	public static final String MyPREFERENCES = "MyPrefs" ;
	
	protected static final int REQUEST_CODE_PICK_FILE_OR_DIRECTORY = 1;
	
	private static final String MY_EXTRA = "WYW.magazine.EXTRA_MY_EXTRA";
	
	Context context;
	
	String tc,tv;
	
	ImageView iv;
	
	TextView bt;
	
	EditText ed1,ed2;
	
	
	 private static int RESULT_LOAD_IMAGE = 1;
	  
	 
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Get the view from listview_main.xml
		setContentView(R.layout.submitphoto);
		
	iv=(ImageView)findViewById(R.id.imageView1);
	bt=(TextView)findViewById(R.id.but);
	ed1=(EditText)findViewById(R.id.editText1);
	ed2=(EditText)findViewById(R.id.editText2);
	
	
	bt.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			
			
			Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	//		intent.setType("image/*");
	//		intent.addCategory(Intent.CATEGORY_OPENABLE);
			
			Toast.makeText(getApplicationContext(), "CLICKED", Toast.LENGTH_SHORT).show();
			
			
			
			
			try {
				startActivityForResult(intent, REQUEST_CODE_GET_CONTENT);
				 
			
			    
			} catch (ActivityNotFoundException e) {
				// No compatible file manager was found.
			//	Toast.makeText(this, "No file Manager installed", 
			//			Toast.LENGTH_SHORT).show();
			}
	    	
			
			
		}
		
		
	});
		
	}
	
	
	public void browse(View v){
		
		
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		
		
		
		
		try {
			startActivityForResult(intent, REQUEST_CODE_GET_CONTENT);
			 
		  
		
		    
		} catch (ActivityNotFoundException e) {
			// No compatible file manager was found.
	//	Toast.makeText(this, "No file Manager installed", 
		//			Toast.LENGTH_SHORT).show();
		}
	}

	
	
	
	File imgFile;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		
		
		
		
		switch (requestCode) {
		case REQUEST_CODE_PICK_FILE_OR_DIRECTORY:
			if (resultCode == RESULT_OK && data != null) {
				// obtain the filename
				Uri fileUri = data.getData();
				if (fileUri != null) {
					String filePath = fileUri.getPath();
					if (filePath != null) {
						tc=filePath;
					}					
					tv="additional extra: " + data.getStringExtra(MY_EXTRA);
				}
			}
			break;
		case REQUEST_CODE_GET_CONTENT:
			if (resultCode == RESULT_OK && data != null) {
				String filePath = null;
				long fileSize = 0;
				String displayName = null;
				Uri uri = data.getData();
				Cursor c = getContentResolver().query(uri, new String[] {MediaStore.MediaColumns.DATA,
					MediaStore.MediaColumns.MIME_TYPE,
					MediaStore.MediaColumns.DISPLAY_NAME,
					MediaStore.MediaColumns.SIZE
				}, null, null, null);
				if (c != null && c.moveToFirst()) {
					int id = c.getColumnIndex(Images.Media.DATA);
					if (id != -1) {
						filePath = c.getString(id);
					}
					displayName = c.getString(2);
					fileSize = c.getLong(3);
				}
				if (filePath != null) {
					tc=filePath;
		//			String strFileSize = getString(R.string.get_content_info,
		//					displayName, "" + fileSize);
					tv=""+fileSize;
					
					imgFile = new  File(tc);
					
				}
			}
		}
		
		
	//	InputStream is = getClass().getResourceAsStream(tc);
	//	iv.setImageDrawable(Drawable.createFromStream(is, ""));
		
			
		if(imgFile!=null)
		{
		if(imgFile.exists()){

		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

		//    iv.setImageBitmap(myBitmap);
		    Bitmap scaled;
		    
		    

		    scalefactor=myBitmap.getWidth()/2;
		    	
		    	
		    
			 nh = (int) ( myBitmap.getHeight() * (scalefactor / myBitmap.getWidth()) );
				
		    scaled = Bitmap.createScaledBitmap(myBitmap,(int) scalefactor, nh, true);  
		    
		    iv.setImageBitmap(scaled);
		
		    ready=true;
		
		}
		}

		
	//	getExternalFilesDir(Environment.DIRECTORY_PICTURES)
		
		
	

		      
		    
		//   iv.setBackgroundResource(tc);
		    
		   // cropImageView.setFixedAspectRatio(true);
		//  aspartic();
		  
		    
			
   	
		        

		
		
		
	}

	int nh;
    
	boolean ready=false;
	
	float scalefactor=512;
	
	public void send(View vi){
		
		String cont;
		
		
		if(ed1.getText().toString()!=null&&ed2.getText().toString()!=null&&iv!=null&&ready==true)
		{		
		
		cont=ed1.getText().toString()+"\n"+ed2.getText().toString();
	
          
          Intent i = new Intent(Intent.ACTION_SEND);
  	
          
          
          i.setType("message/rfc822");
  		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"whatyouwantmagazine@gmail.com"});
  		i.putExtra(Intent.EXTRA_SUBJECT, "Article Submission by ");
  		
  		i.putExtra(Intent.EXTRA_TEXT   , cont);
  		
  		
  	
  		i.setType("image/jpeg");
          File bitmapFile = new File(tc);
        Uri  myUri = Uri.fromFile(bitmapFile);
          i.putExtra(Intent.EXTRA_STREAM, myUri);
  		
  		
  		try {
  		    startActivity(Intent.createChooser(i, "Send mail..."));
  		    
  		   
  		} catch (android.content.ActivityNotFoundException ex) {
  		    Toast.makeText(Submitphoto.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
  		}
          
		}
          
	}
	
	
	public void back(View w){
		
		
		onBackPressed();
		
		
	}
	
	
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		

	  
		
	}
	
}
