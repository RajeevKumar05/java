package com.demo.designpatterns.observer;

import java.util.ArrayList;

public abstract class Observer {
	ArrayList<Observer> observers = new ArrayList<Observer>();
	public void attchObserver(Observer obj){this.observers.add(obj);};
	public void detachObserver(Observer obj){this.observers.remove(obj);};
	public void notifyObserver(double d){
		for(int i=0;i<observers.size();i++){
			Observer o = this.observers.get(i);
			o.showNotification(d);
		}
	};
	public abstract void showNotification(double d);
}
