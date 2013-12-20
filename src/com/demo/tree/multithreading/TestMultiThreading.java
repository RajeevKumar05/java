package com.demo.tree.multithreading;

public class TestMultiThreading implements Runnable{
	private int index;
	public TestMultiThreading(int idx){
		this.index = idx;
	}
	public void run() {
		TaskPerformer tf = new TaskPerformer();
		tf.performNewTask();
	}
	
	public static void main(String[] s){
		TestMultiThreading tester = null;
		for(int i=0;i<5;i++){
			tester = new TestMultiThreading(i);
			Thread t = new Thread(tester,i+"");
			//t.setDaemon(true);
			t.start();
			//new Thread(tester).setDaemon(true)).start();
		}
	}

}
