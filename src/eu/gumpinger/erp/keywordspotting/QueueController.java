package eu.gumpinger.erp.keywordspotting;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QueueController {

	private volatile static QueueController uniqueInstance;
	
	private static int produceTaskSleepTime = 2;
	
	//private static int times=0;
	
	public static ThreadPoolExecutor threadPool=null;

	  private QueueController() {
		  threadPool = new ThreadPoolExecutor(1, 1, 3,
					TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(30),
					new ThreadPoolExecutor.DiscardOldestPolicy());
	  }

	public void addthread(String incomefilename,int incomingID,int inclientID) throws Exception{
		//System.out.println("so far "+times+" times");
		threadPool.execute(new Kws(incomefilename,incomingID ,inclientID ));
		//times++;
		Thread.sleep(produceTaskSleepTime);
	}
	
	  public static QueueController getInstance() {
		    if(uniqueInstance == null) { 
		      synchronized(QueueController.class) {
		        if(uniqueInstance == null) { 
		          uniqueInstance = new QueueController();
		        }
		      }
		    }
		    return uniqueInstance;
		  }
	
}
