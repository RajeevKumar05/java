package com.demo.adt;

import java.util.ArrayList;
import com.demo.tree.bst.Node;

public class MyQueue<E> {
	private ArrayList<E> q = new ArrayList<E>();
	private int front;
	private int rear;
	
	public MyQueue(){		
		this.front = 0;
		this.rear = 0;
	}
	
	public void enqueue(E obj){
		q.add(obj);
		this.front ++;
	}
	public E dequeue(){
		if(this.front == 0)
			return null;
		E ret = this.q.get(this.rear);
		this.q.remove(this.rear);
		this.front --;
		return ret;
	}
	
	public static void main(String[] s){
		MyQueue<String> mq = new MyQueue<String>();
		mq.enqueue("1");
		mq.enqueue("2");
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		System.out.println(mq.dequeue());
		
		MyQueue mq1 = new MyQueue();
		mq1.enqueue(1);
		mq1.enqueue(2);
		mq1.enqueue(new Node(5));
		Object dq = mq1.dequeue();
		while(dq!=null){
			if(dq instanceof Node){
				System.out.print("Node : ");
				((Node)dq).printData();
			}else{
				System.out.println("Integer : "+(Integer)dq);
			}
			dq = mq1.dequeue();
		}
	}
}
