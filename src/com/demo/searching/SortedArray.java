package com.demo.searching;

public class SortedArray {
	
	public static int findPivot(int[] A,int s,int e){
		if(s>e)
			return -1;
		if(s==e)
			return s;
		int m=(s+e)/2;
		if(A[m]>=A[e])
			return findPivot(A,m+1,e);
		else
			return findPivot(A,s,m);		
	}
	public static int binarySearch(int[] A,int s,int e,int key){
		if(s>e)
			return -1;
		if(s==e)
			return s;
		int m=(s+e)/2;
		//System.out.println("Start = "+s+", Mid = "+m+", End = "+e);
		if(A[m]<key)
			return binarySearch(A,m+1,e,key);
		else
			return binarySearch(A,s,m,key);
	}
	public static int findKey(int[] A,int key){
		int p=findPivot(A,0,A.length-1);
		if(p<0)
			return -1;
		//System.out.println("Pivot location = "+p);
		if(key>A[A.length-1])
			return binarySearch(A,0,p-1,key);
		else
			return binarySearch(A,p,A.length-1,key);
	}
	public static int findKey(int[] A,int s,int e,int key){
		if(s>e)
			return -1;
		if(s==e)
			return s;
		int m=(s+e)/2;
		if(A[m] > A[e]){
			if(key <= A[e])
				return findKey(A,m+1,e,key);
			else
				return findKey(A,s,m,key);
		}else{
			if(key>=A[s])
				return findKey(A,s,m-1,key);
			else
				return findKey(A,m,e,key);
		}		
	}
	
	public static int[] nextPalindrome(int[] A){
		int size = A.length;
		int l=-1,r=-1;
		//System.out.println("size = "+size);
		if(size%2 == 1){
			l = (size-1)/2;
			r = l;
		}else{
			l = (size-1)/2;
			r = l + 1;
		}
		boolean isChanged = false;
		int tl=l,tr=r;
		while(tl>=0&&tr<size){
			if(A[tl]>A[tr]){
				A[tr]=A[tl];
				isChanged=true;
				tr++;
				tl--;
			}else if(A[tl]<A[tr]){
				A[tl]=A[tl]+1;
				A[tr]=A[tl];
				isChanged=true;
				tl--;
				tr++;
			}else{
				tl--;
				tr++;
			}
		}
		if(isChanged)
			return A;
		int c=1,s;		
		while(l>=0 && r<size){
			//System.out.println("l="+l+", r="+r);			
			if(A[l]>A[r]){
				A[r]=A[l];
				l--;
				r++;
				c=0;
			}else if(A[l]<A[r]){
				s = A[l]+c;
				A[l] = s%10;
				c = s/10;
				A[r] = A[l];
				l--;
				r++;				
			}else{
				s = A[l]+c;
				A[l]=s%10;
				A[r]=A[l];
				c=s/10;
				l--;
				r++;
			}
		}
		if(c==0)
			return A;
		else{
			int[] B = new int[A.length+1];
			B[0]=1;B[B.length-1]=1;
			for(int i=1;i<B.length-1;i++)
				B[i]=0;
			return B;
		}
	}
	public static void print(int[] A){
		for(int i=0;i<A.length;i++)
			System.out.print(A[i]);
	}
	
	public static int findStart(int[] A,int l,int r,int key){
		if(l>r)
			return -1;
		int mid = l+(r-l)/2;
		if(A[mid]>key)
			return findStart(A,l,mid-1,key);
		else if(A[mid]<key)
			return findStart(A,mid+1,r,key);
		else{
			if(mid>0 && A[mid-1]==A[mid])
				return findStart(A,l,mid-1,key);
			return mid;
		}
	}
	public static void main(String[] s){
		int[] A={10,12,3,4,5,6,7,8};
		//System.out.println("Pivot Element is : "+A[findPivot(A,0,A.length-1)]);
		int key=8;
		System.out.println("Key "+key+" found at : "+findKey(A,key));
		int[] B = {1,1,1};
		System.out.print("Next Palindrom of: ");print(B);
		System.out.print(" is : ");print(nextPalindrome(B));
		
		int[] C = {1,2,3,4,5,5,5,6,6,7,8};
		System.out.println("\nStart : "+findStart(C,0,C.length-1,8));
	}
}