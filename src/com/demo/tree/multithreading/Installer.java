package com.demo.tree.multithreading;

public class Installer implements Runnable{

	private MySoftware ms;
	public Installer(MySoftware m){
		this.ms = m;
	}
	@Override
	public void run() {
		try {
			this.ms.install();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
