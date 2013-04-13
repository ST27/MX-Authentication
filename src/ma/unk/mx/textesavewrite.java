package ma.unk.mx;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.widget.Toast;

  /**
   * @author Soufiane.T
   */


public class textesavewrite {

	 public void WriteSettings(Context context, String data){
	        FileOutputStream fOut = null;
	        OutputStreamWriter osw = null;
	 
	        try{
	           fOut = context.openFileOutput("compteur2.txt",Context.MODE_PRIVATE);
	            osw = new OutputStreamWriter(fOut);
	            osw.write(data);
	            osw.flush();
	           //popup surgissant pour le r�sultat
	           // Toast.makeText(context, "Settings saved",Toast.LENGTH_SHORT).show();
	            }
	            catch (Exception e) {
	                    //Toast.makeText(context, "Settings not saved",Toast.LENGTH_SHORT).show();
	            }
	            finally {
	               try {
	                      osw.close();
	                      fOut.close();
	                      } catch (IOException e) {
	                              // Toast.makeText(context, "Settings not saved",Toast.LENGTH_SHORT).show();
	                      }
	            }
	       }
	 
	 public String ReadSettings(Context context){
	        FileInputStream fIn = null;
	        InputStreamReader isr = null;
	 
	        char[] inputBuffer = new char[255];
	        String data = null;
	 
	        try{
	         fIn = context.openFileInput("compteur2.txt");
	            isr = new InputStreamReader(fIn);
	            isr.read(inputBuffer);
	            data = new String(inputBuffer);
	           //affiche le contenu de mon fichier dans un popup surgissant
	           // Toast.makeText(context, " "+data,Toast.LENGTH_SHORT).show();
	            }
	            catch (Exception e) {
	                    //  Toast.makeText(context, "Settings not read",Toast.LENGTH_SHORT).show();
	            }
	            finally {
	               try {
	                      isr.close();
	                      fIn.close();
	                      } catch (IOException e) {
	                      // Toast.makeText(context, "Settings not read",Toast.LENGTH_SHORT).show();
	                      }
	            }
	            return data;
	       }
	 
	 
	 public String read(Context context) throws IOException {
		    StringBuilder text = new StringBuilder();
		    //String NL = System.getProperty("");
	        FileInputStream fIn = null;
	        InputStreamReader isr = null;
	        DataInputStream dis = null;
	        BufferedInputStream bis = null;

	        char[] inputBuffer = new char[255];

	        fIn = context.openFileInput("compteur2.txt");
	        bis = new BufferedInputStream(fIn);

	        dis = new DataInputStream(bis);

	        return dis.readLine();
	        }
	        
	        
	       
		  }
	
	

