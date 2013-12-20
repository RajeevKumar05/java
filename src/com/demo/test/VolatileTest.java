package com.demo.test;

public class VolatileTest {
	private int x;
	private volatile int y;
	
	public VolatileTest(int a,int b){
		this.x = a;
		this.y = b;
	}
	
	public boolean test(){
		int t=this.y;
		return true;
	}
	
	public int getVolatile(){
		return this.y;
	}
	
	public void setVolatile(int b){
		this.y = b;
	}
	public static void main(String[] s){
		VolatileTest vt = new VolatileTest(2,3);
		System.out.println(vt.test());
		System.out.println(vt.getVolatile());
		int t = vt.getVolatile();
		System.out.println(t);
		t=t+1;
		System.out.println(t);
	}

}
