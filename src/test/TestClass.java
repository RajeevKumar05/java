package test;

import com.demo.designpatterns.subclass.A;

public class TestClass extends A {
	protected void test(){
		super.test();
		System.out.println("Testing TestClass");
	}
	public static void main(String[] s){
		A t = new TestClass();
		TestClass t1 = new TestClass();
		t1.test();
	}
}
