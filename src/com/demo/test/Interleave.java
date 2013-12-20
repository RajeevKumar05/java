package com.demo.test;

public class Interleave {
	public void interleave(String s1,String s2,char[] o,int idx){
		if(idx == o.length){
			print(o);
			return;
		}
		if(s1 != null){
			o[idx] = s1.charAt(0);
			interleave(s1.length()==1?null:s1.substring(1),s2,o,idx+1);
		}
		if(s2!=null){
			o[idx] = s2.charAt(0);
			interleave(s1,s2.length()==1?null:s2.substring(1),o,idx+1);
		}		
	}
	
	public void print(char[] o){
		for(int i=0;i<o.length;i++){
			System.out.print(o[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] s){
		Interleave intr = new Interleave();
		String s1 = "AB";
		String s2 = "CD";
		char[] o = new char[s1.length()+s2.length()];		
		intr.interleave(s1, s2, o, 0);
	}
}
