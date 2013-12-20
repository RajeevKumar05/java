package com.demo.designpatterns.innerclass;

public class TestAnonymous {
	private IInner listner;
	public void addListner(IInner iinn){
		this.listner = iinn;
	}
	public IInner getListner(){
		return this.listner;
	}
	public static void main(String[] s) throws InterruptedException{		
		TestAnonymous ta = new TestAnonymous();
		ta.addListner(new IInner(){
			public void show(){
				System.out.println("This is anonymous class implementation.");
		}});
		IInner lis = ta.getListner();
		lis.show();
		System.out.println("--------------------------");
		Thread.sleep(1000);
		
		Container1 ct1 = new Container1();
		TestAnonymous ta1 = new TestAnonymous();
		ta1.addListner(ct1);
		IInner lis1 = ta1.getListner();
		lis1.show();
	}
}
