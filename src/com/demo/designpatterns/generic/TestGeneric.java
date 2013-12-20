package com.demo.designpatterns.generic;

public class TestGeneric {
	public <T extends Area> double calculateArea(T shape){
		return shape.computeArea();
	}
	public static void main(String[] s){
		Circle c = new Circle(3.0);
		TestGeneric tg = new TestGeneric();
		System.out.println("Circle Area = "+tg.calculateArea(c));
		
		Square sq = new Square(5);
		System.out.println("Square Area = "+tg.calculateArea(sq));
		
		Rectangle rc = new Rectangle(4,5);
		System.out.println("Rectangle Area = "+tg.calculateArea(rc));
		
		//NewShape ns = new NewShape(5);
		//System.out.println("NewShape Area = "+tg.calculateArea(ns));
	}
}
