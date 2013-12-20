package com.demo.test;

public class MaximumCoins {
	
	public static int maxCoins(int[] coins,int left,int right,boolean isATurn){
		if(left>right)
			return 0;
		else if(isATurn){			
			return max(coins[left]+maxCoins(coins,left+1,right,false),coins[right]+maxCoins(coins,left,right-1,false));
		}else
			return min(maxCoins(coins,left+1,right,true),maxCoins(coins,left,right-1,true));
	}
	
	public static int max(int a,int b){
		return a>b?a:b;
	}
	public static int min(int a,int b){
		return a<b?a:b;
	}
	public static void main(String[] s){
		int[] coins = {1,2,3,4,5,4};
		System.out.println(maxCoins(coins,0,coins.length-1,true));
	}

}
