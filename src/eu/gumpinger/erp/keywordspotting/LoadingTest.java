package eu.gumpinger.erp.keywordspotting;

import java.io.IOException;

public class LoadingTest {

	private static int userID = 34;
	private static int clientID = 5;
	private static String contactphone = "4366482617747";
	private static String timestamp = "20121010150750";
	private static String filename = userID + "_" + contactphone + "_"
			+ timestamp;

	public static void main(String argus[]) {

		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(new Looper());
	        t.start();
		}

	}
	
	private static class Looper
    implements Runnable {
    public void run() {
    	KwsTest test = new KwsTest();
		try {
			test.run(filename, userID, clientID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
	
}
