package com.demo.designpatterns.observer;

public class ServiceProvider extends Observer{
	private double price = 0.0;
	public void updatePrice(double d){
		this.price = this.price + d;
		this.notifyObserver(this.price);
	}
	public void showNotification(double d) {
	}	
}
