package com.demo.tree.multithreading;

public class MultiThreading_1 extends Thread{
	private int idx;
	public MultiThreading_1(int i){
		this.idx = i;
	}
	public void run(){
		//System.out.println("Thread index = "+this.idx);
		TaskPerformer tf = new TaskPerformer();
		System.out.println("Thread index = "+this.idx);
		tf.performNewTask();
	}
	
	public static void main(String[] s){
		for(int i=0;i<5;i++){
			MultiThreading_1 mt = new MultiThreading_1(i);
			mt.start();
		}
	}
}
