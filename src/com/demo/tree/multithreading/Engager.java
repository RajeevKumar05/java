package com.demo.tree.multithreading;

public class Engager implements Runnable{
	private MySoftware ms;
	public Engager(MySoftware m){
		this.ms = m;
	}
	
	@Override
	public void run() {
		try {
			this.ms.showSomething();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
