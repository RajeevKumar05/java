package com.demo.list;

public class Value {
	private int data;
	public Value(int key){
		this.data = key;
	}
	public Object getData(){
		return this.data;
	}
	public void setData(int dta){
		this.data = dta;
	}
	public boolean equals(Value v){
		if(this.data == v.data)
			return true;
		else
			return false;
	}
	public void showData(){
		Object thisData = this.data;
		if(thisData instanceof Integer){
			System.out.print((Integer)thisData);
		}else{
			System.out.print("Show not supported.");
		}
	}
}
