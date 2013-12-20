package com.demo.tree.multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue{
	private Queue<Integer> list = new LinkedList<Integer>();
	private volatile boolean isFull = false;
	private volatile boolean isEmpty = true;
		
	public boolean produce(Integer item) throws InterruptedException{
		synchronized(list){
			while(isFull){
				System.out.println("Queue is full !!");
				list.wait();
			}			
			System.out.println("Producing "+item);
			list.add(item);
			isEmpty = false;
			if(list.size() == 5)
				isFull = true;
			list.notifyAll();
			return true;
		}
	}	

	public Integer consume() throws InterruptedException{
		synchronized(list){
			while(isEmpty){
				System.out.println("Queue is empty !!");
				list.wait();			
			}			
				System.out.println("Consuming "+list.peek());
				int ret = list.poll();
				isFull = false;
				if(list.size() == 0)
					isEmpty = true;
				list.notifyAll();
				return ret;						
		}
	}
	
	public static void main(String[] s){
		EventQueue pc = new EventQueue();
		Thread t1 = new Thread(new Producer(pc));
		t1.setName("Producer");
		Thread t2 = new Thread(new Consumer(pc));
		t2.setName("Consumer");
		t1.start();
		t2.start();
	}
}
