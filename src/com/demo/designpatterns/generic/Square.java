package com.demo.designpatterns.generic;

public class Square implements Area {
	private double side;
	
	public Square(){
		this.side = 0.0;
	}
	
	public Square(double s){
		this.side = s;
	}
	
	public double computeArea() {
		return this.side*this.side;
	}

}
