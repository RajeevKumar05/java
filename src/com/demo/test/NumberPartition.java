package com.demo.test;

public class NumberPartition {
	
	public static void partition(int n, int[] sol,int idx,int limit){
		if(n==0){
			print(sol,idx);
			return;
		}
		if(n<0)
			return;
		for(int i=limit;i>0;i--){
			sol[idx] = i;
			partition(n-i,sol,idx+1,i);
		}
	}
	
	public static void print(int[] sol,int idx){
		for(int i=0;i<idx;i++){
			System.out.print(sol[i]+",");
		}
		System.out.println();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int num = 5;
		int[] sol = new int[num];
		int idx=0;
		int limit = num;
		
		NumberPartition.partition(num, sol, idx, limit);
	}

}
