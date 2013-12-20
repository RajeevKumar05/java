package com.demo.test;

import java.util.HashMap;
import java.util.Map;

public class Subset {
	static Map<Integer,Boolean> m = new HashMap<Integer,Boolean>();
	public static void print(int[] o){
		for(int i=0;i<o.length;i++)
			System.out.print(o[i]+" ");
		System.out.println();
	}
	public static void subset(int[] in,int[] out,int idx,int k,int cur){
		if(k==0){
			print(out);
			return;
		}
		for(int i=cur;i<=in.length-k;i++){
			if(m.get(in[i]) == null || !m.get(in[i])){
				out[idx]=in[i];
				m.put(in[i], true);
				subset(in,out,idx+1,k-1,i+1);				
			}
			//m.remove(in[i]);
		}
	}
	
	public static void main(String[] s){
		int[] in={1,2,1};
		int k=2;
		int[] out = new int[k];
		subset(in,out,0,k,0);
	}
}
