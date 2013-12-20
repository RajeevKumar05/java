package com.demo.test;

public class MaxCircularArray {
	public static int compute(int[] a){
		int max = a[0];
		int tmax = a[0];
		int size = a.length;
		int st=0,en=0,tst=0,idx1=0,idx2=1;
		while(idx1 != idx2){
			if(tmax < 0){
				tmax = a[idx2];
				tst = idx2;
				idx1 = idx2;
			}else
				tmax = tmax + a[idx2];
			if(tmax >= max){
				max = tmax;
				st = tst;
				en = idx2;
			}
			idx2 = (idx2+1)%size;
		}
		System.out.println("Start = "+st);
		System.out.println("End = "+en);
		return max;
	}
	
	public  static void main(String[] s){
		int[] a = {8, -8, 9, -9, 10, -11, 12};
		System.out.println("Max = "+compute(a));
	}
}
