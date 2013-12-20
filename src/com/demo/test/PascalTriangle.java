package com.demo.test;

public class PascalTriangle {
	public static void print(int[] o,int l){
		for(int i=0;i<=l;i++)
			System.out.print(o[i]);
		System.out.println();
	}
	public static void printPascal(int n){
		if(n<=0)
			return;
		int[] a = new int[n];
		for(int i=0;i<n;i++){
			a[0]=1;a[i]=1;
			for(int j=i-1;j>0;j--){
				a[j]=a[j]+a[j-1];
			}
			print(a,i);
		}
	}
	
	public static void main(String[] s){
		printPascal(6);
	}
}
