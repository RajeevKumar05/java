package com.demo.designpatterns.tests;

import com.demo.designpatterns.observer.ConcreteObserver1;
import com.demo.designpatterns.observer.ConcreteObserver2;
import com.demo.designpatterns.observer.ServiceProvider;

public class TestObserver {
	public static void main(String[] s){
		ServiceProvider sp = new ServiceProvider();
		ConcreteObserver1 co1 = new ConcreteObserver1();
		sp.attchObserver(co1);
		sp.updatePrice(22.0);
		System.out.println("#################################");
		ConcreteObserver2 co2 = new ConcreteObserver2();
		sp.attchObserver(co2);
		sp.updatePrice(23.8);
		System.out.println("#################################");
		sp.updatePrice(-45.0);
	}
}
