package com.demo.test;

public class FirstOccurence {
	public static int firstOne(int[] arr,int st,int en){
		if(st==en)
			return st;
		int mid = (st+en)/2;
		if(arr[mid] == 1)
			return firstOne(arr,st,mid);
		else
			return firstOne(arr,mid+1,en);
	}
	
	public static void main(String[] s){
		int[] n = {1,1,1};
		System.out.println("First occ of 1 = "+firstOne(n,0,2));
	}
}
