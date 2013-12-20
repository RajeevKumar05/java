package com.demo.dp;

public class Recursion {
	public int noOfWays(int s,int c,int m){
		if(s==0)
			return 1;
		if(s<0)
			return 0;
		int ret = 0;
		while(c<=m){
			ret = ret + noOfWays(s-c,c,m);
			c++;
		}
		return ret;
	}
	
	public static void main(String[] s){
		int n = 8;
		Recursion rc = new Recursion();
		System.out.println(rc.noOfWays(8,1,3));
	}
}
