package ma.unk.mx;



public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(OCRA.generateOCRA("OCRA-1:HOTP-SHA256-6:QN08-PSHA1","1111", "4998", "1111"));
		System.out.println("OCRA-1:HOTP-SHA256-6:QN08-PSHA1@4997".split("@")[0]);
		//System.out.println("OCRA-1:HOTP-SHA256-6:QN08-PSHA1"[1])
	}

}
