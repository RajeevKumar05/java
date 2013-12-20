package com.demo.tree.multithreading;

public class MyThread implements Runnable {
	private Thread t;
	public MyThread(String name){
		t = new Thread(this,name);
		t.start();
	}
	public void run() {		
		TaskPerformer tf = new TaskPerformer();		
		tf.performTask();
	}
	
	public static void main(String[] s){		
		for(int i=0;i<10;i++){			
			new MyThread(""+i);
		}
		System.out.println("Main Thread Completed.");
	}

}
