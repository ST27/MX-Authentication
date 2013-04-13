package ma.unk.mx;




import ma.unk.mx.R;

import android.provider.Settings.System;   

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;

import android.telephony.TelephonyManager;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class CR2Activity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	 ProgressBar myProgressBar;
	 int myProgress = 0;
	 public static int  seed = 0;
	 public static String algo="";
	 
	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.menu4);
        //ed2 = new TextView(this); 
       // ed2 =new TextView(this); 
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
        Spinner spinner = (Spinner)this.findViewById(R.id.spinner1);
       
        @SuppressWarnings({ "rawtypes" })
		ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, new Integer[] {   
                      1,2,3});
        spinner.setAdapter(spinnerArrayAdapter);
    
    
        
	

      
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.getDeviceId();
        
		
        }        
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()== R.id.button1){
			 TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			 @SuppressWarnings("unused")
			String androidID = System.getString(this.getContentResolver(),System.ANDROID_ID);

			//androidID/*telephonyManager.getDeviceId()*/);
			//TOTP.actualiseTime();
			 TOTP.actualiseTime();
			EditText ed = (EditText) findViewById(R.id.editText1);
			ed.setInputType(InputType.TYPE_CLASS_NUMBER);
		    ed.setTransformationMethod(PasswordTransformationMethod.getInstance());
			EditText edx = (EditText) findViewById(R.id.seed);

			TextView ed2 = (TextView) findViewById(R.id.textView4);

	        @SuppressWarnings("unused")
			String k1 = ed.getText().toString();
	        

	        @SuppressWarnings("unchecked")
			Integer st = (Integer) ((AdapterView<SpinnerAdapter>) this.findViewById(R.id.spinner1)).getSelectedItem();
	
	        if(st==1){algo="SHA1";}
	        if(st==2){algo="SHA256";}
	        if(st==3){algo="SHA512";}
		 
	        
	        
	       
	        EditText digits = (EditText) findViewById(R.id.digits);
	        
	        
	        String dig = digits.getText().toString();

	        String challenge = "OCRA-1:HOTP-"+algo+"-"+dig+":QN08-PSHA1";
	        String pass = edx.getText().toString();
	        @SuppressWarnings("unused")
			String str1 = telephonyManager.getDeviceId();
            String str = OCRA.generateOCRA(challenge,"346872344411538", pass, ed.getText().toString() );
          
            ed2.setText(str); 
         
            

		}
		}
} 



