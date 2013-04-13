package ma.unk.mx;

import ma.unk.mx.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

  /**
   * @author Soufiane.T
   */


public class testActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

	setContentView(R.layout.test);
	 Button btvoir = (Button) findViewById(R.id.btvoir);
	 Button btecrire = (Button) findViewById(R.id.btecrire);
   	 
	 final Context lecontext=getBaseContext();

	 btvoir.setOnClickListener(new Button.OnClickListener() {
	    public void onClick(View v) {
	    	  textesavewrite tr = new textesavewrite();

	            String j = tr.ReadSettings(lecontext);
	    } 
	     });  
	        		        		
	btecrire.setOnClickListener(new Button.OnClickListener() {
	         public void onClick(View v) {
	          TextView datatext = (TextView) findViewById(R.id.text);
	         String sQuantite = datatext.getText()+"\n";
	    	  textesavewrite tr = new textesavewrite();

	         tr.WriteSettings(lecontext,sQuantite);
	   } 
	  });
	
}}
