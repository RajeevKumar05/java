package test;

public class Test {
	private static int x = 1;	
	public int getVal(){
		try{
			x = x+1;
			return x;
		}catch(Exception e){
			return 0;
		}finally{
			x = x+1;		
		}
	}
	
	public static void main(String[] s){
		Test t = new Test();
		System.out.println("Function returns : "+t.getVal());
		System.out.println("Valu : "+t.x);
		
		String s1 = new String("X");
		String s2 = new String("Y");
		String s3=s1;
		s1=null;
		System.out.println(s3);
		
		int n = 0x1;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
		n = n<<4 | n;
		System.out.println(n);
	}
}
