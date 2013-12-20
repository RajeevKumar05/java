package com.demo.designpatterns.factory;

public class Mathematics implements Language {
	private String expr = "2 + 3 - 1 +1 = 5";
	
	public Mathematics(int flg){
		
	}
	
	public Mathematics(){
		
	}
	public void show() {
		System.out.println(expr);
	}
}
