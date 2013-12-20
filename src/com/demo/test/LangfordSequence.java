package com.demo.test;

public class LangfordSequence {
	public void printLangford(int n){
		if(n<=0)
			return;
		int[] ls = new int[2*n];
		int[] a = new int[n];
		for(int i=0;i<n;i++)
			a[i]=i+1;
		for(int i=0;i<2*n;i++)
			ls[i]=0;
		
		printLangfordUtil(a,ls,0,0);
		
	}
	public void swap(int[] a,int idx,int i){
		int t=a[idx];
		a[idx]=a[i];
		a[i]=t;
	}
	public int nextPos(int[] ls,int lidx){
		for(int i=lidx+1;i<ls.length;i++)
			if(ls[i]==0)
				return i;
		return -1;
	}
	public void printLangfordUtil(int[] a,int[] ls,int idx,int lidx){
		if(idx==a.length){
			print(ls);
			return;
		}
		if(lidx<0 || lidx>=ls.length)
			return;
		for(int i=idx;i<a.length;i++){
			swap(a,idx,i);
			if(lidx+a[idx] +1 <ls.length && ls[lidx+a[idx] +1 ]==0){
				ls[lidx]=a[idx];
				ls[lidx+a[idx] +1]=a[idx];
				printLangfordUtil(a,ls,idx+1,nextPos(ls,lidx));
				ls[lidx+a[idx] +1]=0;
				ls[lidx]=0;
			}
			swap(a,idx,i);
		}
	}	
	public void print(int[] ls){
		for(int i=0;i<ls.length;i++)
			System.out.print(ls[i]+" ");
		System.out.println();
	}
	
	public static void main(String[] s){
		LangfordSequence ls = new LangfordSequence();
		ls.printLangford(5);
	}
}
