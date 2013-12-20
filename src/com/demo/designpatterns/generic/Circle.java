package com.demo.designpatterns.generic;

public class Circle implements Area {
	private double radius;
	public Circle(){
		this.radius = 0.0;
	}
	public Circle(double r){
		this.radius = r;
	}
	public double computeArea() {		
		return 3.14*this.radius*this.radius;
	}

}
