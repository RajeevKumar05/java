package com.demo.test;

public class SubsetSum {
	public void print(int[] s,int idx){
		for(int i=0;i<idx;i++)
			System.out.print(s[i]+" ");
		System.out.println();
	}
	public void printSubset(int[] a,int sum,int aidx, int[] s, int sidx){
		if(sum == 0){
			print(s,sidx);
			return;
		}
		if(aidx >= a.length)
			return;
		s[sidx] = a[aidx];
		printSubset(a,sum-a[aidx],aidx+1,s,sidx+1);
		printSubset(a,sum,aidx+1,s,sidx);
	}
	
	public void printSubset(int[] a,int sum,int aidx, int[] s, int sidx,int ssize){
		if(sum == 0){
			if(ssize == 0)
				print(s,sidx);
			return;
		}
		if(aidx >= a.length)
			return;
		s[sidx] = a[aidx];
		printSubset(a,sum-a[aidx],aidx+1,s,sidx+1,ssize-1);
		printSubset(a,sum,aidx+1,s,sidx,ssize);
	}
	
	public static void main(String[] ss){
		int[] a = {1,2,3,4,5,6,7,8,9,10,-2,-3,-4,-6};
		int[] s = new int[a.length];
		SubsetSum t = new SubsetSum();
		t.printSubset(a, 30, 0, s, 0,5);
	}
}
