package com.demo.tree.multithreading;

public class Consumer implements Runnable{
	EventQueue pc;
	public Consumer(EventQueue p){
		this.pc = p;
	}
	@Override
	public void run() {		
		boolean flag = true;		
		while(flag){			
				try {
					Thread.sleep(1500);
					this.pc.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
					flag = false;
				}
		}		
	}	
}
