package com.demo.test;

public class ZeroesAtLast {
	public static void main(String[] s){
		int[] arr = {1,0,4,5,0,0,6,4,8,0};
		int l = arr.length;
		int p = 0,q=l-1;		
		while(p<q){
			if(arr[p]==0){
				arr[p] = arr[q];
				arr[q]=0;
				q--;
			}else{
				p++;
			}
		}
		
		for(int i=0;i<l;i++){
			System.out.print(arr[i]+",");
		}
	}

}
