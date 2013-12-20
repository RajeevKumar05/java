package com.demo.test;

import java.util.Stack;

public class ArrayTest {
	public static int[] mergeSort(int[] a,int st,int ed){
		if(st==ed){
			int[] ret = new int[1];
			ret[0]=a[st];
			return ret;
		}		
		int mid = (st+ed)/2;
		int[] la=mergeSort(a,st,mid);
		int[] ra=mergeSort(a,mid+1,ed);
		int l=0;
		int r=0;
		int idx=0;
		int[] b = new int[ed-st+1];
		while(l<la.length && r<ra.length){			
			if(la[l]<ra[r]){
				b[idx]=la[l];
				l++;
			}else{
				b[idx]=ra[r];
				r++;
			}
			idx++;
		}
		if(l==la.length){
			while(r<ra.length){
				b[idx]=ra[r];
				r++;
				idx++;
			}
		}else{
			while(l<la.length){
				b[idx]=la[l];
				l++;
				idx++;
			}
		}
		return b;
	}
	public static int compare(int a,int b){		
		Stack<Integer> s1=new Stack<Integer>();
		Stack<Integer> s2=new Stack<Integer>();
		while(a>0){
			s1.push(a%10);
			a=a/10;
		}
		while(b>0){
			s2.push(b%10);
			b=b/10;
		}
		int d1;
		int d2;
		while(!s1.isEmpty() && !s2.isEmpty()){
			d1=s1.pop();
			d2=s2.pop();
			if(d1>d2){
				return 1;
			}else if(d1<d2){
				return -1;
			}
		}
		if(s1.isEmpty() && s2.isEmpty())
			return 0;
		else if(s1.isEmpty() && !s2.isEmpty())
			return 1;
		else
			return -1;
	}
	public static int[] sortOtherWay(int[] a){
		for(int i=1;i<a.length;i++){
			int temp=a[i];
			for(int j=i-1;j>=0;j--){
				if(compare(a[j],temp)<=0){
					a[j+1]=temp;
					break;
				}else{
					a[j+1]=a[j];
					if(j==0)
						a[j]=temp;
				}
			}
		}
		return a;
	}
	
	public static void main(String[] s){
		int[] a={2,5,11,40,8,3};
		int[] a1={7, 76, 7, 7};
		int[] b=sortOtherWay(a1);
		//mergeSort(a,0,a.length-1);
		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+" ");
	}
}
