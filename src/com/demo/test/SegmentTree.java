package com.demo.test;

public class SegmentTree {
	private int[] st;
	private int[] data;
	public SegmentTree(int[] d){
		data = d;
		int l=1;
		int t = d.length;
		//t = t/2;
		while(t!=0){
			l=l*2;
			t=t/2;
		}
		System.out.println("l = "+l);
		st = new int[2*l-1];
		getSegmentSum(d,0,d.length-1,0);
	}
	
	public int getSegmentSum(int[] d,int s,int e,int idx){
		if(s>e || s<0 || e > d.length -1)
			return 0;
		if(s==e){
			st[idx]=d[s];
			return st[idx];
		}
		int m = (s+e)/2;
		System.out.println("idx = "+idx+", s = "+s+", e = "+e);
		st[idx] = getSegmentSum(d,s,m,2*idx+1) + getSegmentSum(d,m+1,e,2*idx+2);
		return st[idx];		
	}
	public void printST(){
		for(int i=0;i<st.length;i++)
			System.out.print(st[i]+" ");
		System.out.println();
	}
	public static void main(String[] s){
		int[] d = {1,2,3};
		SegmentTree st = new SegmentTree(d);
		st.printST();
	}
}
