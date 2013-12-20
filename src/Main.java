import java.util.Scanner;

public class Main {
	public void test(){
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		p(s);
	}
	
	public void p(Object s){
		System.out.println(s);
	}
	public static void main(String[] s){
		new Main().test();
	}
	
}
