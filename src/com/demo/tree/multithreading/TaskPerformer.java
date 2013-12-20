package com.demo.tree.multithreading;

public class TaskPerformer {
	public static final String task = new String("a - b - c");
	public static final Integer[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	public static int count = 1;
	public void performTask(){		
		System.out.println(Thread.currentThread().getName()+" : "+task);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}	
	}
	public void performTask(int milliSecs) throws InterruptedException{
		synchronized(task){
			System.out.println(Thread.currentThread().getName()+" : "+task + " : "+count);
			count ++;
			Thread.sleep(milliSecs);
		}
	}
	public synchronized void showTask(){
		System.out.println(task + " : "+count);
		count ++;
	}
	
	public void performNewTask(){		
		synchronized(this.getClass()){
			System.out.println(Thread.currentThread().getName()+" : "+task + " : "+count);
			count ++;
		}		
	}
	public int sum(int begin,int end){
		System.out.println("Begin = "+begin+", End = "+end);
		int sum = 0;
		for(int i=begin;i<=end;i++){
			sum = sum + arr[i];			
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Returning sum = "+sum);
		return sum;
	}
}
