package com.demo.tree.multithreading;

import java.io.DataInputStream;
import java.io.IOException;

public class MultiThreadedSum implements Runnable {	
	public static Integer total = 0;
	private int begin;
	private int end;
	private int tSum;
	public MultiThreadedSum(int i,int j){
		this.begin = i;
		this.end = j;		
	}
	public void run(){		
		TaskPerformer tf = new TaskPerformer();
		this.tSum = tf.sum(this.begin, this.end);
		synchronized(total){
			total = total + this.tSum;	
		}				
		
	}
	public static int getThreadCount(int size,int slice){		
		if(size % slice == 0)
			return size/slice;
		else{
			return size/slice + 1;
		}
	}
	public static void main(String[] s) throws IOException{
		System.out.print("Enter length slice : ");
		int size = 20;
		DataInputStream d = new DataInputStream(System.in);
		String ls = d.readLine();
		Integer slice = Integer.parseInt(ls.trim());		
		int noOfThread = getThreadCount(size,(int)slice);
		System.out.println("No of threads are : "+noOfThread);
		Thread[] threadPool = new Thread[noOfThread];
		Thread t;
		MultiThreadedSum mts;
		for(int i=0;i<noOfThread;i++){
			if(i < noOfThread -1)
				mts = new MultiThreadedSum(slice*i , slice*i + slice -1);
			else
				mts = new MultiThreadedSum(slice*i, size - 1);
			t = new Thread(mts);
			threadPool[i] = t;
		}
		for(int i=0;i<threadPool.length;i++)
			threadPool[i].start();
		for(int i=0;i<threadPool.length;i++){
			try {
				threadPool[i].join();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("Total = " + total );
	}

}
