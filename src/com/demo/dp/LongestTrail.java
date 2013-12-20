package com.demo.dp;

public class LongestTrail {
	public static int calculateLongestTrail(int[][] mtx){
		int max = 0;
		int t;
		for(int i=0;i<mtx.length;i++){
			for(int j=0;j<mtx[0].length;j++){
				t = getMaxTrail(mtx,i,j);
				if(t>max)
					max = t;
			}
		}
		return max;
	}
	public static int max(int a,int b,int c){
		int max=a;
		if(b>max)
			max = b;
		if(max>c)
			max=c;
		return max;
	}
	public static int getMaxTrail(int[][] mtx, int r,int c){
		if(r<0 || c<0 || r>=mtx.length || c>=mtx[0].length)
			return 0;
		else if(mtx[r][c] == 0)
			return 0;
		else
			return max(getMaxTrail(mtx,r-1,c)+getMaxTrail(mtx,r+1,c)+1,
					getMaxTrail(mtx,r,c-1)+getMaxTrail(mtx,r,c+1)+1,
					getMaxTrail(mtx,r-1,c-1)+getMaxTrail(mtx,r+1,c+1)+1);
	}
	
	public static void main(String[] s){
		int[][] mtx = {{1,0,0},{1,1,1},{0,1,1}};
		System.out.println(calculateLongestTrail(mtx));
	}
}
