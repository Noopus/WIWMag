package com.androidbegin.jsonparsetutorial;




import java.io.File;
import java.util.ArrayList;

import com.ipaulpro.afilechooser.utils.FileUtils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ArticleSubmit extends Activity {
	
	

  
	   SharedPreferences sharedpreferences;
	
	   Button submit;
	
	
	   Coustxt browseimg,browsedoc;
	   
	   TextView pather;

	   ImageView img;
	   
	   
	   EditText ed;
	   
	   
	   protected static final int REQUEST_CODE_GET_CONTENT = 2;
		
	   
	   private static final int PICKFILE_RESULT_CODE = 1;
	   
		protected static final int REQUEST_CODE_PICK_FILE_OR_DIRECTORY = 1;
		
		private static final String MY_EXTRA = "org.openintents.filemanager.demo.EXTRA_MY_EXTRA";
		
		
		 private static final int REQUEST_CODE = 6384; // onActivityResult request
		   
		
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Get the view from listview_main.xml
		setContentView(R.layout.articlesubmit);
		
		
		
		paths=new String[2];
		
		sharedpreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
		
		
		pather=(TextView)findViewById(R.id.textView1);
		
		
		ed=(EditText)findViewById(R.id.editText1);
		
		
		browseimg=(Coustxt)findViewById(R.id.brwsimg);
		
		browsedoc=(Coustxt)findViewById(R.id.brwsdoc);
		
		submit=(Button)findViewById(R.id.submit);
		
		img=(ImageView)findViewById(R.id.imageView1);
		
		
		browseimg.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
		//		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		//		intent.setType("*/*");
		//		intent.addCategory(Intent.CATEGORY_OPENABLE);
			
				
				Intent intent = new Intent(Intent.ACTION_PICK,
	                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				
				
				try {
					startActivityForResult(intent, REQUEST_CODE_GET_CONTENT);
					 
				
				    
				} catch (ActivityNotFoundException e) {
					// No compatible file manager was found.
				//	Toast.makeText(this, "No file Manager installed", 
				//			Toast.LENGTH_SHORT).show();
				}
		    	
				
				
			}
			
			
			
			
			
		});
		
		
		
		
		
		
		browsedoc.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			/*	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	             intent.setType("file/*");
	       startActivityForResult(intent,PICKFILE_RESULT_CODE);
	   */
				
	
		           showChooser();
		           
				
				
			}
			
			
			
			
			
		});
		
		
	
	
	}
	
	
	
	String[] paths;
	
	
	public void send(View vi){
		
		
		
		
		
		Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"pmohansivam@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "Article Submission by "+sharedpreferences.getString("nickname", "").toString());
		i.putExtra(Intent.EXTRA_TEXT   , ed.getText());
		
		
		
        File bitmapFile = new File(tc);
        Uri  myUri = Uri.fromFile(bitmapFile);
    
        
        File fileIn;
        Uri u;  
        
        

        ArrayList<Uri> uris = new ArrayList<Uri>();
        
        
       
        
        if(paths[0]!=null&&paths[1]!=null)
        {  
       /*
        for(int z=0;z<paths.length;z++)
        {
         fileIn = new File(paths[z]);
         
         u= Uri.fromFile(fileIn);
	

         uris.add(u);
         
        }
       */ 
        
        for (int z = 0; z < 2; z++) {
            File file = new File(paths[z]);
            uris.add(Uri.fromFile(file));
        }
        

        System.out.println(uris.get(0).getPath()+" p0 : p1 "+uris.get(1).getPath());
        
        
      //  i.putExtra(Intent.EXTRA_STREAM, uris.get(1));
        
        i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
      	
       
        
        
            
        
		try {
			
			
			
		    startActivity(Intent.createChooser(i, "Send mail..."));
		    
		   
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(ArticleSubmit.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
		
		
        }
        else
        {
    	    Toast.makeText(ArticleSubmit.this, "Incomplete Data . Please recheck", Toast.LENGTH_SHORT).show();
    		
        }
        
        
	}
	
	public void back(View vie){
		
		
		onBackPressed();
	
	}
	
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	 
	    finish();
	}
	

	String[] p;
	
	String path;
	
	  @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        switch (requestCode) {
	            case REQUEST_CODE:
	                // If the file selection was successful
	                if (resultCode == RESULT_OK) {
	                    if (data != null) {
	                        // Get the URI of the selected file
	                        final Uri uri = data.getData();
	                        Log.i("FILE PICKKR", "Uri = " + uri.toString());
	                        try {
	                            // Get the file path from the URI
	                            final String path = FileUtils.getPath(this, uri);
	                            Toast.makeText(ArticleSubmit.this,
	                                    "File Selected: " + path, Toast.LENGTH_LONG).show();
	                            
	                            browsedoc.setText(""+path);
	                            
	                            
	                            this.path=path;
	                            
	                           paths[1]=path;    
	                		        
	                		
	                        } catch (Exception e) {
	                            Log.e("FileSelectorTestActivity", "File select error", e);
	                        }
	                    }
	                }
	                break;
	        
	        
	            case PICKFILE_RESULT_CODE:
	     		   if(resultCode==RESULT_OK){
	     		    String FilePath = data.getData().getPath();
	     		   
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
	    						
	    						paths[0]=filePath;
	    					}
	    					displayName = c.getString(2);
	    					fileSize = c.getLong(3);
	    				}
	    				if (filePath != null) {
	    					tc=filePath;
	    					
	    					
	    				//	String strFileSize = getString(R.string.get_content_info,
	    				//			displayName, "" + fileSize);
	    				//	tv=strFileSize;
	    				}
	    			}
	    		}
	    		
	    	//	getExternalFilesDir(Environment.DIRECTORY_PICTURES)
	    		
	    		File imgFile = new  File(tc);
	    		if(imgFile.exists()){

	    		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

	    		//    img.setImageBitmap(myBitmap);
	    		    
	    		   // cropImageView.setFixedAspectRatio(true);
	    		//  aspartic();
	    		  

	    			//    Bitmap d = new BitmapDrawable(res , imgFile.getAbsolutePath()).getBitmap();
	    			
	    		    
	    		    
	    		    /*
	    			    int nh = (int) ( myBitmap.getHeight() * (1024.0 / myBitmap.getWidth()) );
	    			    
	    			    Bitmap scaled = Bitmap.createScaledBitmap(myBitmap, 1024, nh, true);  
	    			    
	    			    img.setImageBitmap(scaled);
	    			  */
	    		    
	    		    
	    		    scalefactor=myBitmap.getWidth()/1.5f;
			    	
			    	
	    		    
	   			 nh = (int) ( myBitmap.getHeight() * (scalefactor / myBitmap.getWidth()) );
	   				
	   		    scaled = Bitmap.createScaledBitmap(myBitmap,(int) scalefactor, nh, true);  
	   		    
	   		    img.setImageBitmap(scaled);
	   		
	   				    
	    			    
	    			    
	    		    
	    			final int interval = 1000; // 1 Second
	     		
	        }
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	
	
	  
	  
	  int nh;
	    
		boolean ready=false;
		
		float scalefactor=512;

		 Bitmap scaled;
		
		
	String tc="";
	
	/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		
		
	
		super.onActivityResult(requestCode, resultCode, data);

		
		switch(requestCode){

              
		
		
		case PICKFILE_RESULT_CODE:
		   if(resultCode==RESULT_OK){
		    String FilePath = data.getData().getPath();
		    path.setText(FilePath);
		   }
		   break;
		   
		  }
		
		
		switch (requestCode) {

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
				//	String strFileSize = getString(R.string.get_content_info,
				//			displayName, "" + fileSize);
				//	tv=strFileSize;
				}
			}
		}
		
	//	getExternalFilesDir(Environment.DIRECTORY_PICTURES)
		
		File imgFile = new  File(tc);
		if(imgFile.exists()){

		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

		    img.setImageBitmap(myBitmap);
		    
		   // cropImageView.setFixedAspectRatio(true);
		//  aspartic();
		  
		    
			final int interval = 1000; // 1 Second
   		 
   		  

		}
		
		
		
		
		
		
		
	}
	*/
	
	
	private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, "WYW Chooser");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }


}
