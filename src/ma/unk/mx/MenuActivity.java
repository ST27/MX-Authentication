package ma.unk.mx;




import ma.unk.mx.R;

import android.provider.Settings.System;   
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	 ProgressBar myProgressBar;
	 int myProgress = 0;
	 public static int  seed = 0;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);


        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
      
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.getDeviceId();
		
        }        
	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()== R.id.button1){
			 TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			 @SuppressWarnings("unused")
			String androidID = System.getString(this.getContentResolver(),System.ANDROID_ID);

			
			 TOTP.actualiseTime();
			EditText ed = (EditText) findViewById(R.id.editText1);
			TextView ed2 = (TextView) findViewById(R.id.textView4);
	 
	        String k1 = ed.getText().toString() + telephonyManager.getDeviceId();
	        
	        
	
	       
	        ed2.setText(TOTP.generateTOTP(k1, TOTP.steps, "6", "HmacSHA512"));
  
            }


		}
} 



