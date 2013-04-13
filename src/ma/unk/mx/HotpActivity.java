package ma.unk.mx;




import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import ma.m2m.mx.R;

import android.provider.Settings.System;   

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class HotpActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	 ProgressBar myProgressBar;
	 int myProgress = 0;
	 public static int  seed = 0;
	 public static int c=0;
	 String FILENAME = "counter.txt";
	 

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.menu2);

        
   
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
        final Context lecontext=getBaseContext();

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.getDeviceId();
        
		
        }        
	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()== R.id.button1){
			 TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			 String androidID = System.getString(this.getContentResolver(),System.ANDROID_ID);
	    	  textesavewrite tr = new textesavewrite();
	          final Context lecontext=getBaseContext();

		        CompteurBDD cbdd = new CompteurBDD(this);
		        Compteur compteur = new Compteur();

		        cbdd.open();
		        cbdd.insertLivre(compteur);
		 
			 TOTP.actualiseTime(); 
			EditText ed = (EditText) findViewById(R.id.editText1);
	  
			TextView ed2 = (TextView) findViewById(R.id.textView4);

	        String k1 = ed.getText().toString()+telephonyManager.getDeviceId();
	       
	        	
	        HOTP hotp = new HOTP();
	       
	   
	   	 FileOutputStream fos = null;
	
	/*	try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	 	try {
	   	 		fos.write("1".getBytes());
	   	 		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	   	/*
	        try {
				if(tr.read(lecontext).equals(null)){
				fos = openFileOutput("compteur1.txt", Context.MODE_PRIVATE);
				tr.WriteSettings(lecontext, "0")}
				if(Integer.decode(tr.read(lecontext))==null){
				;}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
					//fos.write("0".getBytes());
			
	   	File file = new File("/data/data/ma.m2m.mx/files/compteur2.txt");
	   	boolean l = file.exists();
	   	if(l==false){
	   		//tr.ReadSettings(lecontext);
	   		tr.WriteSettings(lecontext, "0");}
	   Log.d("fzfez", String.valueOf(l));
				if(l==true){
			String j =tr.ReadSettings(lecontext);
			String ous = null;
			try {
				ous = tr.read(lecontext);
				Log.d("fefefe",ous);
} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    int jint = Integer.decode(ous);
		    Log.d("fefefe",String.valueOf(jint));
		    String jstr = String.valueOf(jint);
		    //Log.d("ffa",jstr);
		    String seedstr =String.valueOf(jint);

		     
		 	String str = hotp.gen(k1,jint,6); ///*compteur.getValeur(),6)+*/ "       " + compteur.getValeur();
		   ed2.setText(str+"    "+jint); 
		   jint++;
		   String seedstr1=String.valueOf(jint++);
		   tr.WriteSettings(lecontext,seedstr1);

		}
	   	 	          
		}
	        
	      //  ed2.setText(HOTP.gen(k1,seed,6));
	       // seed++;
	       
	        //Log.d("shit",k1);	
	        
	        /*	        
 * final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
	        new Thread(new Runnable() {
	        	private int progressStatus = 100;
				public void run() {
					// TODO Auto-generated method stub
					
					   while (progressStatus > 1) 
		                {
		                    progressStatus = doSomeWork();
		                  Handler handler = new Handler();  
		                    //---Update the progress bar---
		                    handler.post(new Runnable() 
		                    {
		                        public void run() {
		                        	bar.setProgress(progressStatus);
		                        }
		                    });
		                }}

					    private int doSomeWork() 
			            {
			                try 
			                {
			                    //---simulate doing some work---
			                    Thread.sleep(50);

			                } catch (InterruptedException e) 
			                {
			                   e.printStackTrace();
			                }

			                int progress = progressStatus - 1;

			                return progress;
			            }

			        }).start();  */

		}
} 



