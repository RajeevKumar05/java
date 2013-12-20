package com.demo.tree.multithreading;

public class Producer implements Runnable{
	EventQueue pc;
	public Producer(EventQueue p){
		this.pc = p;
	}
	@Override
	public void run() {		
		boolean flag = true;
		int num=0;
		while(flag){			
				try {
					Thread.sleep(1000);
					this.pc.produce(num);
					num ++;
				} catch (InterruptedException e) {					
					e.printStackTrace();
					flag = false;
				}
		}		
	}	
}
