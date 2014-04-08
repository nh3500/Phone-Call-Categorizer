package eu.gumpinger.erp.keywordspotting;

public class QueusTest {

	public static void main(String[] args) {
		QueueController.getInstance();
		
		Tester t1 = new Tester();
		new Thread(t1). start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tester t2 = new Tester();
		new Thread(t2). start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tester t3 = new Tester();
		new Thread(t3). start();
		
	}
}

class Tester implements Runnable{
	
	Tester(){
		
    }
	
	@Override
	public void run() {
		try {
			//QueueController.addthread("/daten/cepstrum/"+"null_+436604867461_20121125152142.mfcc", 34, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
