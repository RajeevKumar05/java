package com.demo.permute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Permutation {
	public static void permute1( String input)
	{
		int inputLength = input.length();
		boolean[ ] used = new boolean[ inputLength ];
		StringBuffer outputString = new StringBuffer();
		char[ ] in = input.toCharArray( );

		doPermute ( in, outputString, used, inputLength, 0 );

	}

	public static void doPermute ( char[ ] in, StringBuffer outputString, 
			boolean[ ] used, int inputLength, int level)
	{
		if( level == inputLength) {
			System.out.println ( outputString.toString()); 
			return;
		}

		for( int i = 0; i < inputLength; ++i )
		{       

			if( used[i] ) continue;

			outputString.append( in[i] );      
			used[i] = true;       
			doPermute( in,   outputString, used, inputLength, level + 1 );       
			used[i] = false;
			System.out.println("Out length = "+outputString.length());
			outputString.setLength(   outputString.length() - 1 );   
		}
	}
	public static void testStringBuffer(String s){
		char[] str = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		sb.append(s);
		int l = s.length();
		System.out.println(sb.toString());
		sb.setLength(l-1);
		System.out.println(sb.toString());
		sb.append('b');
		System.out.println(sb.toString());
	}
	
	public Collection<String> arbitrate(char c,String t){
		Collection<String> res = new ArrayList<String>();
		res.add(c+t);
		for(int i=1;i<t.length();i++){
			res.add(t.substring(0,i)+c+t.substring(i));
		}
		res.add(t+c);
		System.out.println(res.size());
		return res;
	}
	public Collection<String> arbitrate(char c, Collection<String> t){
		System.out.println(c+","+t);
		Collection<String> res = new ArrayList<String>();
		Iterator<String> itr = t.iterator();
		while(itr.hasNext()){
			res.add(c+itr.next());
		}
		return res;
	}
	
	public Collection<String> permute(String s){
		Collection<String> result = new ArrayList<String>();
		if(s.length()==2){
			String c1 = String.valueOf(s.charAt(0));
			String c2 = String.valueOf(s.charAt(1));
			result.add(c1+c2);
			result.add(c2+c1);
			return result;
		}
		
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i); 
			result.addAll(arbitrate(c,permute(s.substring(0,i)+s.substring(i+1))));
		}
		return result;
	}
	public static void print(char[] s){
		for(int i=0;i<s.length;i++)
			System.out.print(s[i]);
		System.out.println();
	}
	public static void inplacePermute(char[] s,int i){
		if(i==s.length-1){
			print(s);
			return;
		}
		char c = s[i];
		for(int k=i;k<s.length;k++){			
			s[i]=s[k];
			s[k]=c;
			inplacePermute(s,i+1);			
			s[k]=s[i];
			s[i]=c;			
		}
	}
	
	public static void print(int[] a,int length){
		for(int i=0;i<length;i++)
			System.out.print(a[i]);
		System.out.println();
	}
	
	public static void permute(int[] a,int length,int idx,int st){
		if(length == idx)
			print(a,length);
		int temp;
		for(int i=st;i<a.length;i++){
			temp = a[i];
			a[i] = a[idx];
			a[idx] = temp;
			permute(a,length,idx+1,st+1);
			a[idx]=a[i];
			a[i]=temp;
		}
	}
	
	public static void main(String[] s){
		//permute("dogs");
		//testStringBuffer("dog");
//		Collection<String> permutations = (new Permutation()).permute("ABCD");
//		Iterator<String> itr = permutations.iterator();
//		while(itr.hasNext())
//			System.out.println(itr.next());
		String str = "ABC";
		//System.out.println(str.toCharArray().toString());
		char[] src = str.toCharArray();
		inplacePermute(src,0);
		System.out.println();
		print(src);
		
		int[] a = {1,2,3};
		permute(a,2,0,0);
	}
}
