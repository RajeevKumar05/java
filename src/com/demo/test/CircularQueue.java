package com.demo.test;

import java.io.DataInputStream;
import java.io.IOException;

public class CircularQueue {
	private int[] queue;
	private int tail,head,count,size;
	private int ERROR=-9999;
	
	public boolean initialize(int n){
		if(this.queue != null){
			System.out.println("Queue is already intialized.");
			return false;
		}		
		synchronized(this){
			if(this.queue == null){
				this.queue = new int[n];
				this.size=n;
				this.tail=0;
				this.head=0;
				this.count=0;
				return true;
			}else{
				System.out.println("Queue is already initialized.");
				return false;
			}
			
		}		
	}
	
	//Method to enqueue an integer to the queue.
	//Returns true if successfully added integer to the queue.
	//Returns false otherwise
	public boolean enqueue(int data){
		if(this.queue == null){
			System.out.println("Queue is not initialized yet.");
			return false;
		}
		synchronized(this.queue){			
			if(this.size == this.count){
				System.out.println("Queue is full.");
				return false;
			}else{
				this.queue[this.head]=data;
				this.head = (this.head+1)%this.size;
				System.out.println("Head = "+this.head);
				this.count++;
				return true;
			}			
		}
	}
	
	//Method to dequeue, Returns integer in FIFO manner.
	//Returns -9999 if queue is not initialize or empty.
	public int dequeue(){
		if(this.queue == null){
			System.out.println("Queue is not initialized yet.");
			return ERROR;
		}
		synchronized(this.queue){
			if(this.count == 0){
				System.out.println("Queue is empty");
				return ERROR;
			}else{
				this.count--;
				int ret = this.queue[this.tail];
				this.tail = (this.tail+1)%this.size;
				return ret;
			}
		}		
	}
	
	public static void main(String[] s) throws IOException{
		CircularQueue q = new CircularQueue();
		q.initialize(2);
		String st;
		while(true){
			DataInputStream d = new DataInputStream(System.in);
			System.out.print("Enter : E 2 | D | X :: ");
			st = d.readLine();
			if("X".equals(st))
				break;
			else if("D".equalsIgnoreCase(st))
				System.out.println(q.dequeue());
			else{
				q.enqueue(Integer.parseInt(st.split(" ")[1]));
			}
			
		}		
		System.out.println(q.enqueue(1));
		System.out.println(q.enqueue(2));
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}

}
