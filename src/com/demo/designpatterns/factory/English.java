package com.demo.designpatterns.factory;

public class English implements Language {
	private String content = "This is english.";
	public English(){
		System.out.println("Created English language.");
	}
	public English(String str){
		System.out.println("Other");
	}
	public void show() {
		System.out.println(content);
	}

}
