package com.demo.designpatterns.innerclass;

public class TestInnerClass {
	public static void main(String[] s){
		Container outer = new Container();
		outer.showVariabes();
		Container.Inner inner = outer.new Inner();
		System.out.println("Bigger of 4,5 = "+inner.findBigger(4, 5));		
	}
}
