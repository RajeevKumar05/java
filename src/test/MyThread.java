package test;

public class MyThread extends Thread {
	
	public void run() {		
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			//
		}
		System.out.println("Thread output.");
	}	
}
