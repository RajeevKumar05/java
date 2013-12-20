package com.demo.designpatterns.observer;

public class ConcreteObserver1 extends Observer {
	public void showNotification(double d){
		System.out.println(this.getClass().getCanonicalName()+" - Current Stock price = "+d);
	}
}
