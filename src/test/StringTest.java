package test;

import java.util.Hashtable;

public class StringTest {
	public static void main(String[] s){
		String s1 = new String("XYZ");
		String s2 = new String("XYZ");
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
		Hashtable<String,Integer> ht = new Hashtable<String,Integer>();
		ht.put(s1, 1);
		System.out.println(ht.size());
		ht.put(s2, 0);
		System.out.println(ht.size());
	}
}
 