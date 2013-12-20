package com.demo.inheritence;

public class Child extends Base {
	public static void print1(){
		System.out.println("Child");
	}
	
	public static void main(String[] s){
		Base b = new Child();
		b.show();
		Child.print();
		Base.print();
		Child.print1();
	}
}
