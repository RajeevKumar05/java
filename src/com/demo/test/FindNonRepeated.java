package com.demo.test;

public class FindNonRepeated {
	public static int findNonRepeated(int[] A,int st,int en){
		if(A==null || A.length==0)
			return -1;
		if(st==en)
			return A[st];
		int mid=st+(en-st)/2;
		System.out.println("st="+st+", en="+en+", mid="+mid);
		
			if(A[mid]==A[mid-1]){
				if((mid-st+1)%2==0){
					return findNonRepeated(A,mid+1,en);
				}else
					return findNonRepeated(A,st,mid);
			}else if(A[mid]==A[mid+1]){
				if((mid-st+1)%2==0){
					return findNonRepeated(A,st,mid-1);
				}else
					return findNonRepeated(A,mid+2,en);
			}else
				return A[mid];
				
	}
	
	public static void main(String[] s){
		int [] A={-1,-1,1,1,2,3,3,4,4,5,5};
		System.out.println(findNonRepeated(A,0,A.length-1));
	}
}
