package com.demo.designpatterns.subclass;

public class A {
	private String name;
	public int salary;
	public A(){
		name = "Initialize Name";
		salary = -1;
	}
	public A(String name){
		this.name = name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	protected void test(){
		System.out.println("Testing A");
	}
}
