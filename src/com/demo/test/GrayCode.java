package com.demo.test;

public class GrayCode {
	public void print(int[] c){
		if(c == null)
			return;
		for(int i=0;i<c.length;i++)
			System.out.print(c[i]);
		System.out.println();
	}
	public void generateCode(int n,int idx,int[] code){
		if(n<=0){
			print(code);
			return;
		}
		code[idx]=0;
		generateCode(n-1,idx+1,code);
		code[idx]=1;
		generateCode(n-1,idx+1,code);		
	}
	
	public static void main(String[] s){
		GrayCode gc = new GrayCode();
		int n=2;
		int[] code = new int[n];
		gc.generateCode(n,0,code);
		char c = 'c' & ('c'-1);
		System.out.println('a'+0);
		System.out.println('b'+0);
		System.out.println('*'+0);
		int t = '*';
		System.out.println(t);
		System.out.println('a' & '*');
		System.out.println(new Integer(1).toBinaryString(255));
		int x = 255 & 0x0080 >> 7;
		System.out.println(x);
	}

}
