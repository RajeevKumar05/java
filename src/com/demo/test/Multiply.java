package com.demo.test;

public class Multiply {
	public static void print(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print("a["+i+"]="+a[i]+",");
		}
	}
	public static String mul(String s1,String s2){
		char[] num1 = s1.toCharArray();
		char[] num2 = s2.toCharArray();
		int size = num1.length + num2.length + 1;
		String s="";
		int[] ans = new int[size];
		for(int i=0;i<size;i++){
			ans[i]=0;
		}
		int carry = 0;
		int kp = 0;
		int lp = 0;
		int m,sum,r;
		for(int k=num2.length -1 ;k>=0;k--){
			lp=0;
			carry = 0;
			for(int l=num1.length -1;l>=0;l--){
				m = Integer.parseInt(num2[k]+"")*Integer.parseInt(num1[l]+"");
				//System.out.print(m+",");
				sum = ans[kp+lp]+m+carry;
				carry = sum/10;
				r=sum%10;
				//System.out.println(sum+","+carry+","+r);
				ans[kp+lp]=r;
				//print(ans);
				lp++;
			}
			ans[kp+lp]=carry;
			kp++;
		}
		size--;
		boolean sZero = true;
		while(size>=0){
			if(ans[size] != 0)
				sZero = false;
			if(!sZero){
				s = s + ans[size];
			}
			size--;
		}
		return s;		
	}
	
	public static void main(String[] s){
		String s1="11";
		String s2="1000";
		System.out.println(mul(s1,s2));
	}
}
