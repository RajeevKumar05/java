package test;

public class BaseTest {
	public void showBaseTest(){
		System.out.println("Running Base Test");
	}
	protected void finalize(){
		System.out.println("Destroying BaseTest");
	}
}
