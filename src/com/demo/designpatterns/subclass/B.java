package com.demo.designpatterns.subclass;

public class B extends A {
	private String fName;
	private String lName;
	public B(){
		//super(null);
		this.fName = null;
		this.lName = null;
		this.salary = 0;
	}
	public B(String fName,String lName){
		super(fName+" "+lName);
		this.fName = fName;
		this.lName = lName;
	}
	
	public String fetchName(){
		return this.getName();
	}
	
	protected void test(){
		System.out.println("Testing B");
	}
	
	public static void main(String[] s){
		B tester = new B();
		System.out.println(tester.fetchName()+", Salary = "+tester.salary);
	}
}
