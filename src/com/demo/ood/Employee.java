package com.demo.ood;

import java.io.Serializable;

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;
	private transient int idx=0;
	private int id;
	private String name;
	
	//public Employee(){};
	
	public Employee(int id,String name){
		this.idx = this.idx + 1;
		this.id = id;
		this.name = name;
	}
	
	public void print(){
		System.out.println("IDX = "+this.idx);
		System.out.println("ID = "+this.id);
		System.out.println("Name = "+this.name);
	}
}
