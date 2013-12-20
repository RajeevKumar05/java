package com.demo.test;

public class StaleObjectTest {
	private int[] t = new int[2];
	public StaleObjectTest(){
		t[2]=3;
	}
	
	public void test(){
		int i=0;
		while(i<5){
			System.out.println("######################");
			i++;
		}
	}
	
	public static void main(String[] s){
		StaleObjectTest sot = new StaleObjectTest();
		sot.test();
	}
}
