package com.demo.designpatterns.innerclass;

public class Container {
	public int counter;
	private String nature;
	public Container(){
		System.out.println("Instantiating Outer class.");
		this.counter = 0;
		this.nature = "Outer";
	}
	public Container(int count,String nat){
		System.out.println("Instantiating Outer class.");
		this.counter = count;
		this.nature = nat;
	}
	
	public void showVariabes(){
		System.out.println("Counter = "+this.counter);
		System.out.println("Nature = "+this.nature);
		Inner inner = new Inner();
		System.out.println("Bigger of 2,3 = "+inner.findBigger(2, 3));
		Inner inner1 = new Inner();
		System.out.println("Bigger of 5,3 = "+inner1.findBigger(5, 3));		
		class LocalInner{
			public LocalInner(){
				System.out.println("Instantiating Local Inner.");
			}
			public void updateVar(){
				counter = counter +1;
				nature = "Local Inner";
				System.out.println("Counter = "+counter);
				System.out.println("Nature = "+nature);
			}
		}
		LocalInner li = new LocalInner();
		li.updateVar();
	}	
	public class Inner{
		public Inner(){
			counter = counter + 1;
			nature = "Inner";
			System.out.println("Instantiating Inner class.");
		}
		public <T extends Comparable<T>> T findBigger(T a,T b){
			System.out.println("Counter = "+counter);
			System.out.println("Nature = "+nature);			
			T bigger;
			if(a.compareTo(b) > 0)
				bigger = a;
			else
				bigger = b;
			return bigger;
		}
	}
}
