package com.demo.test;

public class TernaryString {
	public static int count(char[] a,int idx,char[] b,int uc){		
		if(idx<a.length){
			if(uc==0){
				b[0] = a[idx];
				b[1] = '0';
				return 1+count(a,idx+1,b,1)+count(a,idx+1,b,0);
			}else{
				if(b[1]=='0'){
					if(a[idx]!=b[0]){
						b[1]=a[idx];
						return 1+count(a,idx+1,b,2);
					}else{
						return 1+count(a,idx+1,b,1);
					}					
				}else{
					if(a[idx]==b[0] || a[idx]==b[1])
						return 1+count(a,idx+1,b,2);
					else{
						return 0;
					}
				}
			}
		}else
			return 0;
	}
	public static int getCount(String s){
		char[] b = new char[2];
		return count(s.toCharArray(),0,b,0);
	}
	public static void main(String[] s){
		System.out.println(getCount("baaccb"));
	}
}
