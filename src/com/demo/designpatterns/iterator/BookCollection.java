package com.demo.designpatterns.iterator;

public class BookCollection implements IContainer{
	
	private String[] list = {"Hello World","00000","007","James","Bond"};
	
	public BookCollection(){		
	}
	public BookCollection(String[] list){
		this.list = list;
	}
	
	public BookIterator getIterator(){
		return (new BookIterator(0));
	}
	
	private class BookIterator implements IIterator{
		private int index;
		private int SIZE = list.length;
		public BookIterator(int idx){
			this.index = idx;
		}
		public boolean hasNext(){
			if(index < SIZE)
				return true;
			else
				return false;
		}
		public String next(){
			this.index++;
			return list[this.index - 1];
		}
	}
}
