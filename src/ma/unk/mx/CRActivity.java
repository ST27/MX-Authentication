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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CRActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	 ProgressBar myProgressBar;
	 int myProgress = 0;
	 public static int  seed = 0;
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.menu3);
        //ed2 = new TextView(this); 
       // ed2 =new TextView(this); 
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
   

      //  Button c = findViewById(R.id.button)
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.getDeviceId();
        
      //  ProgressBar myProgressBar=(ProgressBar)findViewById(R.id.progressBar1);
      //  new Thread(myThread).start();

		
        }        
	
	
	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()== R.id.button1){
			 TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			 @SuppressWarnings("unused")
			String androidID = System.getString(this.getContentResolver(),System.ANDROID_ID);

	
		
			 TOTP.actualiseTime();
			EditText ed = (EditText) findViewById(R.id.editText1);
			ed.setInputType(InputType.TYPE_CLASS_NUMBER);
		    ed.setTransformationMethod(PasswordTransformationMethod.getInstance());
			EditText edx = (EditText) findViewById(R.id.editText2);

			TextView ed2 = (TextView) findViewById(R.id.textView4);

	        @SuppressWarnings("unused")
			String k1 = ed.getText().toString();
	        
	        
	        
	       
	
	        String challenge = edx.getText().toString().split("@")[0];
	        String pass = edx.getText().toString().split("@")[1];
            String str = OCRA.generateOCRA(challenge, ed.getText().toString(), pass, telephonyManager.getDeviceId());
           ed2.setText(str); 
      
            
		}
	        
	  
		}
} 



