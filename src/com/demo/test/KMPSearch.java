package com.demo.test;

public class KMPSearch {

	public int match(String text,String pat){		
		char[] t = text.toCharArray();
		char[] p = pat.toCharArray();
		int[] lps = this.preProcess(p);
		this.print(lps);
		int i=0;
		int j=0;
		while(i<t.length && j<p.length){			
			if(t[i+j] == p[j]){
				if(j == p.length-1)
					return i;
				else
					j++;
			}else{
				i = i + j - lps[j] + 1;
				j = lps[j];
			}			
		}
		return -1;
	}
	
	public int[] preProcess(char[] p){
		int[] lps = new int[p.length];
		int j;
		for(int i=0;i<p.length;i++){
			j = 0;
			lps[j]=0;
			while(j<i){
				if(p[j] != p[i-j])
					break;
				j++;
			}
			lps[i]=j;
		}
		return lps;
	}
	
	public void print(int[] lps){
		for(int i=0;i<lps.length;i++)
			System.out.print(lps[i]+" ");
		System.out.println();
	}
	
	public static void main(String[] s){
		String text = "AABBC DEG AABBCCCDDHG FGHJ";
		String pat = "AABABCC";
		KMPSearch kmp = new KMPSearch();
		System.out.println("Match at : "+kmp.match(text, pat));
	}
}
