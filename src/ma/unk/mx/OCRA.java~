package ma.unk.mx;
import javax.crypto.Mac;
  import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

  /**
   * This an example implementation of OCRA.
   * Visit www.openauthentication.org for more information.
   *
   * @author Johan Rydell, PortWise  Edited by Soufiane.T
   */
  public class OCRA {

      private OCRA() {}

      /**
       * This method uses the JCE to provide the crypto
       * algorithm.
       * HMAC computes a Hashed Message Authentication Code with the
       * crypto hash algorithm as a parameter.
       *
       * @param crypto     the crypto algorithm (HmacSHA1, HmacSHA256,
       *                                   HmacSHA512)
       * @param keyBytes   the bytes to use for the HMAC key
       * @param text       the message or text to be authenticated.
       */

/*
      public static byte[] hmac_sha1(byte[] keyBytes, byte[] text)
    	         throws NoSuchAlgorithmException, InvalidKeyException
    	 {
    	     // try {
    	     Mac hmacSha1;
    	     try
    	     {
    	         hmacSha1 = Mac.getInstance("HmacSHA512");
    	     } catch (NoSuchAlgorithmException nsae)
    	     {
    	         hmacSha1 = Mac.getInstance("HMAC-SHA-512");
    	     }
    	     SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
    	     hmacSha1.init(macKey);
    	     return hmacSha1.doFinal(text);
    	 }

*/      
      private static byte[] hmac_sha1(String crypto,
                       byte[] keyBytes, byte[] text){
          Mac hmac = null;
          try {
              hmac = Mac.getInstance(crypto);
              SecretKeySpec macKey =
                  new SecretKeySpec(keyBytes, "HMAC_SHA256_MAC_NAME");

              hmac.init(macKey);
              return hmac.doFinal(text);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }


      /*public static byte[] hmac_sha1(String crypto, byte[] keyBytes,
              byte[] text){
    	  //net.rim.device.api.crypto.SHA512Digest digest = null;
     	 HMACKey macKey;
     	  HMAC hmac = null;
     	  Digest digest=null;
     	  if(crypto.equals("HmacSHA1")){
     		 digest = new net.rim.device.api.crypto.SHA1Digest();  
     	  }
     	  if(crypto.equals("HmacSHA256")){
      		 digest = new net.rim.device.api.crypto.SHA256Digest();  
      	  }
     	  if(crypto.equals("HmacSHA512")){
      		 digest = new net.rim.device.api.crypto.SHA512Digest();  
      	  }

          try {
              //MAC hmac;
         	
         		//digest = new net.rim.device.api.crypto.SHA512Digest();
         	macKey=new HMACKey(keyBytes);
              hmac=new HMAC(macKey, digest);
              hmac.update(text);
              
          } catch (CryptoException gse) {
             
          }
          return hmac.getMAC();
      }*/
      
      
      private static final int[] DIGITS_POWER
      // 0 1  2   3    4     5      6       7        8
      = {1,10,100,1000,10000,100000,1000000,10000000,100000000 };

      
      public static byte[] intToByteArray(long decim) {
          byte[] b = new byte[4];
          for (int i = 0; i < 4; i++) {
              int offset = (b.length - 1 - i) * 8;
              b[i] = (byte) ((decim >>> offset) & 0xFF);
          }
          return b;
      }
      
      /**
       * This method converts HEX string to Byte[]
       *
       * @param hex   the HEX string
       *
       * @return      A byte array
       */
   /*   private static byte[] hexStr2Bytes(String hex){
          // Adding one byte to get the right conversion
          // values starting with "0" can be converted
         // byte[] bArray = new BigInteger("10" + hex,16).toByteArray();

          
          int puissance = 1;
          int s = 0;
          long decim = 0;
          String hexfinal = "10" + hex;

          for (s = 0; s < hexfinal.length() - 1; s++)
          {
              puissance = 1;
              for (int j = 0; j < hexfinal.length() - 1 - s; j++)
              {
                  puissance = puissance * 16;
              }
              char c =hexfinal.charAt(s);
              decim = decim + (Integer.parseInt(Character.toString(c)) * puissance);
          }
         
          char q = hexfinal.charAt(hexfinal.length() - 1);
          decim = decim + Integer.parseInt(Character.toString(q));
         // byte[] bArray = intToByteArray(decim);
          byte[] bArray = String.valueOf(decim).getBytes();
         // byte[] ret = new byte[bArray.length - 1];
          // Copy all the REAL bytes, not the "first"
          byte[] ret = new byte[bArray.length - 1];
          System.arraycopy(bArray, 1, ret, 0, ret.length);
          return ret;
      }*/
      
      private static byte[] hexStr2Bytes(String hex){
          // Adding one byte to get the right conversion
          // values starting with "0" can be converted
          int puissance = 1;
          int s=0;
          int decim=0;
          String hexfinal = "10"+hex;

          for (s = 0; s < hexfinal.length() - 1; s++)
          {
              puissance = 1;
              for (int j = 0; j < hexfinal.length() - 1 - s; j++)
              {
                  puissance = puissance * 16;
              }
              //String c=String.valueOf(hexfinal.charAt(s));
              decim = decim + (Integer.parseInt(String.valueOf(hexfinal.charAt(s))) * puissance);
          }
          decim = decim + Integer.parseInt(String.valueOf(hexfinal.charAt(hexfinal.length() - 1)));
          byte[] bArray=String.valueOf(decim).getBytes();
          // Copy all the REAL bytes, not the "first"
          byte[] ret = new byte[bArray.length - 1];
          System.arraycopy(bArray, 1, ret, 0, ret.length);
          return ret;
      }
      public static String randomNumbers(int returnDigits, int max,String algo) {
    	    Random r = new Random();
    	    int rndNums ;
    	  String OCRA = "OCRA-1:HOTP-"+algo+"-"+returnDigits+":QN08-PSHA1";
    	        rndNums = r.nextInt(max);
    	    return OCRA+"-"+rndNums;
    	}

      /**
       * This method generates an OCRA HOTP value for the given
       * set of parameters.
       *
       * @param ocraSuite    the OCRA Suite
       * @param key          the shared secret, HEX encoded
       * @param counter      the counter that changes on a per use
       *                     basis, HEX encoded
       * @param question     the challenge question, HEX encoded
       * @param password     a password that can be used, HEX encoded
       * @param sessionInformation Static information that identifies
       *                     the current session, Hex encoded
       * @param timeStamp    a value that reflects a time
       *
       * @return A numeric String in base 10 that includes
       * {@link truncationDigits} digits
       * 
 */
      static public String generateOCRA(String ocraSuite,String key,String question,String password){

          int codeDigits = 0;
          String crypto = "";
          String result = null;
          int ocraSuiteLength = (ocraSuite.getBytes()).length;
          int questionLength = 0;
          int passwordLength = 0;
          
          // The OCRASuites components
          String CryptoFunction = ocraSuite.split(":")[1];
          String DataInput = ocraSuite.split(":")[2];

          if(CryptoFunction.toLowerCase().indexOf("sha1") > 1)
              crypto = "HmacSHA1";
          if(CryptoFunction.toLowerCase().indexOf("sha256") > 1)
              crypto = "HmacSHA256";
          if(CryptoFunction.toLowerCase().indexOf("sha512") > 1)
              crypto = "HmacSHA512";

          // How many digits should we return
          codeDigits = Integer.decode(CryptoFunction.substring(
                  CryptoFunction.lastIndexOf("-")+1));

          
          // Question - always 128 bytes
         /* if(DataInput.toLowerCase().startsWith("q") ||
                  (DataInput.toLowerCase().indexOf("-q") >= 0)) {
              while(question.length() < 256)
                  question = question + "0";
*/
              questionLength=128;
          

          // Password - sha1
          /*if(DataInput.toLowerCase().indexOf("psha1") > 1){
              while(password.length() < 40)
                  password = "0" + password;*/
              passwordLength=20;
          




          // Remember to add "1" for the "00" byte delimiter
          byte[] msg = new byte[ocraSuiteLength +
                        questionLength +
                        passwordLength +
                        1];


          // Put the bytes of "ocraSuite" parameters into the message
          byte[] bArray = ocraSuite.getBytes();
          System.arraycopy(bArray, 0, msg, 0, bArray.length);

          // Delimiter
          msg[bArray.length] = 0x00;


          // Put the bytes of "question" to the message
          // Input is text encoded
          if(questionLength > 0 ){
              bArray = hexStr2Bytes(question);
              System.arraycopy(bArray, 0, msg, ocraSuiteLength + 1, bArray.length);
              System.out.println(bArray+"   "+passwordLength);

          }

          // Put the bytes of "password" to the message
          // Input is HEX encoded


          if(passwordLength > 0){
              bArray = hexStr2Bytes(password);
              System.arraycopy(bArray, 0, msg, ocraSuiteLength + 1 + questionLength, bArray.length);

          }


          // Put the bytes of "time" to the message
          // Input is text value of minutes

          bArray = hexStr2Bytes(key);

          byte[] hash = hmac_sha1(crypto, bArray, msg);

          // put selected bytes into result int
          int offset = hash[hash.length - 1] & 0xf;

          int binary =
              ((hash[offset] & 0x7f) << 24) |
              ((hash[offset + 1] & 0xff) << 16) |
              ((hash[offset + 2] & 0xff) << 8) |
              (hash[offset + 3] & 0xff);

         // int otp = binary % DIGITS_POWER[codeDigits];
          int otp = (int) (binary % Math.pow(10, codeDigits));

          result = Integer.toString(otp);
          while (result.length() < codeDigits) {
              result = "0" + result;
          }
          return result;
      }
  }
            	  
