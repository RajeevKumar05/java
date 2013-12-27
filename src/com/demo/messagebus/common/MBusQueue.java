package com.demo.messagebus.common;

import java.util.Iterator;
import java.util.Queue;

public class MBusQueue {
	public Queue<Message> queue;
	
	public MBusQueue(Queue<Message> queue){
		this.queue = queue;
	}
	
	public void add(Message msg){
		System.out.println("Adding to queue.");
		System.out.println("Before, Queue size = "+this.queue.size());
		this.queue.offer(msg);
		System.out.println("After, Queue size = "+this.queue.size());
	}
	
	public Message poll(){
		return this.queue.poll();
	}
	
	public void list(){
		Iterator<Message> itr = this.queue.iterator();
		Message m;
		while(itr.hasNext()){
			m = itr.next();
			System.out.println(m.toString());
			System.out.println("---------------------------------------------------------");
		}
	}
	
	public int size(){
		return this.queue.size();
	}
}
