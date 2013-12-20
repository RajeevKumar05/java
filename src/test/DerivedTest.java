package test;

public class DerivedTest extends BaseTest {
	protected void finalize(){
		this.showBaseTest();
		System.out.println("Destroying Derived Test");
	}
	
	public static void main(String[] s) throws InterruptedException{
		DerivedTest dt = new DerivedTest();
		//dt.finalize();
		//Thread.sleep(5000);
		//System.gc();
		//Thread.sleep(5000);
		dt.showBaseTest();
	}
}
