package test;

public class Main {
	public static void main(String[] s){
		MyThread t = new MyThread();
		//t.run();
		t.start();
		System.out.println("Main output.");
		
		java.util.Map m = new java.util.HashMap();
	}
}
