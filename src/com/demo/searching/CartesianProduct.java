package com.demo.searching;

public class CartesianProduct {
	public static void print(int[] p){
		for(int i=0;i<p.length;i++)
			System.out.print(p[i]+" ");
		System.out.println();
	}
	public static void cProduct(int n,int[] p,int[] k){
		if(n==k.length){
			print(p);
			return;
		}
		for(int i=0;i<k[n];i++){
			p[n]=i;
			cProduct(n+1,p,k);
		}
	}
	public static void main(String[] s){
		int[] k = {2,2,2,2};
		int[] p = new int[k.length];
		cProduct(0,p,k);
	}
}
