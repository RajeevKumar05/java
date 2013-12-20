package com.demo.designpatterns.tests;

import com.demo.designpatterns.iterator.BookCollection;
import com.demo.designpatterns.iterator.IIterator;

public class TestIteratorPattern {
	public static void main(String[] s){
		BookCollection bc = new BookCollection();
		IIterator itr = bc.getIterator();
		while(itr.hasNext()){
			System.out.println("- "+itr.next());
		}
		System.out.println("---------------------------");
		String[] list = {"Rajeev","Kumar"};
		BookCollection bc1 = new BookCollection(list);	
		IIterator itr1 = bc1.getIterator();
		while(itr1.hasNext()){
			System.out.println("- "+itr1.next());
		}
	}
}
