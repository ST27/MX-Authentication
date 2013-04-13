package ma.unk.mx;


import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * This is an example implementation of the OATH
 * TOTP algorithm.
 * Visit www.openauthentication.org for more information.
 *
 * @author Johan Rydell, PortWise, Inc. edited by Soufiane.T
 */

public class TOTP {
 static String seed64="";
 static String steps = "0";
 static  String steps2 = "0";
    TOTP() {
    
            }

    /**
     * This method uses the JCE to provide the crypto algorithm.
     * HMAC computes a Hashed Message Authentication Code with the
     * crypto hash algorithm as a parameter.
     *
     * @param crypto: the crypto algorithm (HmacSHA1, HmacSHA256,
     *                             HmacSHA512)
     * @param keyBytes: the bytes to use for the HMAC key
     * @param text: the message or text to be authenticated
     */





    private static byte[] hmac_sha(String crypto, byte[] keyBytes,
            byte[] text){
        try {
            Mac hmac;
            hmac = Mac.getInstance(crypto);
           // System.out.println(hmac);
            SecretKeySpec macKey =
                new SecretKeySpec(keyBytes, "RAW");
           // System.out.println(macKey);

           hmac.init(macKey);
            
            return hmac.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }


    /**
     * This method converts a HEX string to Byte[]
     *
     * @param hex: the HEX string
     *
     * @return: a byte array
     */

    private static byte[] hexStr2Bytes(String hex){
        // Adding one byte to get the right conversion
        // Values starting with "0" can be converted
        byte[] bArray = new BigInteger("10" + hex,16).toByteArray();

        // Copy all the REAL bytes, not the "first"
        byte[] ret = new byte[bArray.length - 1];
        for (int i = 0; i < ret.length; i++)
            ret[i] = bArray[i+1];
        return ret;
    }

    private static final int[] DIGITS_POWER
    // 0 1  2   3    4     5      6       7        8
    = {1,10,100,1000,10000,100000,1000000,10000000,100000000 };














    /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded
     * @param time: a value that reflects a time
     * @param returnDigits: number of digits to return
     *
     * @return: a numeric String in base 10 that includes
     *              {@link truncationDigits} digits
     */

    public static String generateTOTP(String key,
            String time,
            String returnDigits){
        return generateTOTP(key, time, returnDigits, "HmacSHA256");
    }


    /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded
     * @param time: a value that reflects a time
     * @param returnDigits: number of digits to return
     *
     * @return: a numeric String in base 10 that includes
     *              {@link truncationDigits} digits
     */

    public static String generateTOTP256(String key,
            String time,
            String returnDigits){
        return generateTOTP(key, time, returnDigits, "HmacSHA256");
    }
  /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded
     * @param time: a value that reflects a time
     * @param returnDigits: number of digits to return
     *
     * @return: a numeric String in base 10 that includes
     *              {@link truncationDigits} digits
     */

    public static String generateTOTP512(String key,
            String time,
            String returnDigits){
        return generateTOTP(key, time, returnDigits, "HmacSHA512");
    }


    /**
     * This method generates a TOTP value for the given
     * set of parameters.
     *
     * @param key: the shared secret, HEX encoded
     * @param time: a value that reflects a time
     * @param returnDigits: number of digits to return
     * @param crypto: the crypto function to use
     *
     * @return: a numeric String in base 10 that includes
     *              {@link truncationDigits} digits
     */

    public static void actualiseTime(){
        double random = Math.random();
        long T0 = 0;
        long X = 30;
        Date d = new Date();
        Long k = d.getTime()/1000;    

        long testTime[] = {d.getTime()};
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
                    

            for (int i=0; i<testTime.length; i++) {
                long T = (k - T0)/X;
                long T2 = ((k - T0)/X)-1;
               //  System.out.println(T);
                steps = Long.toHexString(T).toUpperCase();
                steps2 = Long.toHexString(T2).toUpperCase();
                while (steps.length() < 16) steps = "0" + steps;
                String fmtTime = String.format("%1$-11s", k/1000);
                String utcTime = df.format(new Date(testTime[i]));
                
            }
            }
    
    
    
    
    
    
    
    
    public static String generateTOTP(String key,
            String time,
            String returnDigits,
            String crypto){
        int codeDigits = Integer.decode(returnDigits).intValue();
        String result = null;

        // Using the counter
        // First 8 bytes are for the movingFactor
        // Compliant with base RFC 4226 (HOTP)
        System.out.println(time);
        while (time.length() < 16 )
            time = "0" + time;

        // Get the HEX in a Byte[]
        byte[] msg = time.getBytes();
        System.out.println("message is : " + msg + "\n");
        byte[] k = key.getBytes();




        byte[] hash = hmac_sha(crypto, k, msg);
     //   System.out.println(hash);
        // put selected bytes into result int
        int offset = hash[hash.length - 1] & 0xf;

        int binary =
            ((hash[offset] & 0x7f) << 24) |
            ((hash[offset + 1] & 0xff) << 16) |
            ((hash[offset + 2] & 0xff) << 8) |
            (hash[offset + 3] & 0xff);

        int otp = binary % DIGITS_POWER[codeDigits];

        result = Integer.toString(otp);
        while (result.length() < codeDigits) {
            result = "0" + result;
        }
        return result;
    }}
