package com.demo.dp;

public class Bucket {
	public int noOfWays(int n){
		if(n==0)
			return 1;
		else if(n<0)
			return 0;
		else
			return (noOfWays(n-1)+noOfWays(n-2)+noOfWays(n-3));
	}
	public static void main(String[] s){
		Bucket b = new Bucket();
		System.out.println(b.noOfWays(3));
	}
}
