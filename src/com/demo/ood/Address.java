package com.demo.ood;

import java.io.Serializable;

public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	private String city;
	private String state;
	
	public Address(){};
	
	public Address(String ct,String st){
		this.city = ct;
		this.state = st;
	}
	
	public void print(){
		System.out.println("City = "+this.city);
		System.out.println("State = "+this.state);
	}
}
