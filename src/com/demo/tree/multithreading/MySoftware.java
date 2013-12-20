package com.demo.tree.multithreading;

public class MySoftware {
	private volatile boolean installed = false;;
	public void install() throws InterruptedException{
		int ct = 0;
		while(ct < 10){
			Thread.sleep(1000);
			ct++;
		}
		installed = true;
	}	
	public void showSomething() throws InterruptedException{
		while(!installed){
			System.out.print(".");
			Thread.sleep(200);
		}
	}
	
	public static void main(String[] s){
		MySoftware ms = new MySoftware();
		Installer ins = new Installer(ms);
		Engager eng = new Engager(ms);
		Thread it = new Thread(ins);
		Thread et = new Thread(eng);
		it.start();
		et.start();
	}
}
