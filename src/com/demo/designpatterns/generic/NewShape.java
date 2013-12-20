package com.demo.designpatterns.generic;

public class NewShape {
	private double ecli;
	public NewShape(){
		this.ecli = 0.0;
	}
	public NewShape(double ecli){
		this.ecli = ecli;
	}
	public double couputeArea(){
		return 5.4*this.ecli;
	}
}
