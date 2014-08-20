package com.androidbegin.jsonparsetutorial;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class submitcontents extends Activity {
	
	

	public static final String MyPREFERENCES = "MyPrefs" ;
	
	
	  
	   
	   public static final String nickName = "nickKey"; 
	  
	   SharedPreferences sharedpreferences;
	Button bt;
	
	TextView tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Get the view from listview_main.xml
		setContentView(R.layout.submitcontents);
		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		
		
		tv=(TextView)findViewById(R.id.textView1);
		
		
		bt=(Button)findViewById(R.id.button1);
		
		String terms="The copyrights of the contents submitted by the user is shared by the What You Want Magazine. By submitting the content the user agrees to give rights to WYW Magazine to Reproduce and Republish the contents of the user in any version, place and time.The copyrights of the contents submitted by the user is shared by the What You Want Magazine. By submitting the content the user agrees to give rights to WYW Magazine to Reproduce and Republish the contents of the user in any version, place and time.The copyrights of the contents submitted by the user is shared by the What You Want Magazine. By submitting the content the user agrees to give rights to WYW Magazine to Reproduce and Republish the contents of the user in any version, place and time.The copyrights of the contents submitted by the user is shared by the What You Want Magazine. By submitting the content the user agrees to give rights to WYW Magazine to Reproduce and Republish the contents of the user in any version, place and time.The copyrights of the contents submitted by the user is shared by the What You Want Magazine. By submitting the content the user agrees to give rights to WYW Magazine to Reproduce and Republish the contents of the user in any version, place and time.The copyrights of the contents submitted by the user is shared by the What You Want Magazine. By submitting the content the user agrees to give rights to WYW Magazine to Reproduce and Republish the contents of the user in any version, place and time.";
		
		
		
		
		
		tv.setText(terms);
		
	
	
	
	
    	
	
	
	
	}
	
	
	public void mail(View vi){
		
		
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"pmohansivam@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "Article Submission by "+sharedpreferences.getString(nickName, "").toString());
		i.putExtra(Intent.EXTRA_TEXT   , "");
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		    
		   
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(submitcontents.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public void back(View vie){
		
		
		onBackPressed();
	
	}
	
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	   
	}
	

}
