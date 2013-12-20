package com.demo.designpatterns.innerclass;

public class DemoAnonymous {
	private int x = 5;
	private int y = 5;
	public String toString(){
		return "From Main class : ("+this.x+","+this.y+")";
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
	public static void main(String[] s){
		DemoAnonymous daMain = new DemoAnonymous();
		DemoAnonymous daAnon = new DemoAnonymous(){
			public String toString(){
				return "From anonymous inner : ("+this.getX()+" | "+this.getY()+")";
			}
		};
		
		System.out.println(daMain.toString());
		System.out.println(daAnon.toString());
	}
}
