package com.demo.designpatterns.generic;

public class Rectangle implements Area {
	private double l;
	private double b;
	public Rectangle(){
		this.l=0;
		this.b=0;
	}
	public Rectangle(double a,double b){
		this.l=a;
		this.b=b;
	}
	public double computeArea() {
		return this.l*this.b;
	}

}
