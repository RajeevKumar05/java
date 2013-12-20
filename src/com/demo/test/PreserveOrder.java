package com.demo.test;

public class PreserveOrder {

	//In-place algorithm to arrange even numbers at even place and odd at odd places, preserves the original order.
	public static int[] rearrange(int[] a){
		if(a == null)
			return a;
		int i = 0;
		while(i<a.length){
			if(a[i]%2 ==0 && i%2 != 0){
				for(int j=i+1;j<a.length;j++){
					if(a[j]%2 != 0){
						swap(i,j,a);
						break;
					}
				}
			}else if(a[i]%2 != 0 && i%2 ==0){
				for(int j=i+1;j<a.length;j++){
					if(a[j]%2 == 0){
						swap(i,j,a);
						break;
					}
				}
			}else{
				//Do nothing
			}
			i++;
		}
		return a;
	}
	
	public static void swap(int i,int j,int[] a){
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	public static void print(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i] + ", ");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,2,3,4,5,6};
		a = rearrange(a);
		print(a);
	}

}
