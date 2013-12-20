package com.demo.test;

public class SpiralPrint {
	public static void printTop(int[][] mtx,int row,int l,int r){
		if(l>r)
			return;
		for(int i=l;i<=r;i++)
			System.out.print(mtx[row][i]+" ");
	}
	public static void printRight(int[][] mtx,int col,int t,int b){
		if(t>b)
			return;
		for(int i=t;i<=b;i++)
			System.out.print(mtx[i][col]+" ");
	}
	public static void printBottom(int[][] mtx,int row,int r,int l){
		if(l>r)
			return;
		for(int i=r;i>=l;i--){
			System.out.print(mtx[row][i]+" ");
		}
	}
	public static void printLeft(int[][] mtx,int col,int b,int t){
		if(t>b)
			return;
		for(int i=b;i>=t;i--)
			System.out.print(mtx[i][col]+" ");
	}
	public static void printSpiral(int[][] mtx){
		int i=mtx[0].length;
		int j=mtx.length;
		System.out.println("L="+i+",H="+j);
		int tlx=0,tly=0,brx=i-1,bry=j-1;
		while(tlx<=brx && tly<=bry){
			if(tlx==brx){
				printRight(mtx,tlx,tly,bry);
			}else if(tly==bry){
				printTop(mtx,tly,tlx,brx);
			}else{
				printTop(mtx,tly,tlx,brx);
				printRight(mtx,brx,tly+1,bry);
				printBottom(mtx,bry,brx-1,tlx);
				printLeft(mtx,tlx,bry-1,tly+1);
			}			
			tlx++;tly++;brx--;bry--;
		}
	}
	
	public static void spiralPrint(int[][] mtx, int r,int c,int rs,int cs){
		if(rs<=0 || cs <=0)
			return;
		for(int i=c;i<c+cs;i++)
			System.out.print(mtx[r][i]+" ");
		for(int i=r+1;i<r+rs;i++)
			System.out.print(mtx[i][c+cs-1]+" ");
		for(int i=c+cs-2;i>=c;i--)
			System.out.print(mtx[r+rs-1][i]+" ");
		for(int i=r+rs-2;i>r;i--)
			System.out.print(mtx[i][c]+" ");
		spiralPrint(mtx,r+1,c+1,rs-2,cs-2);
	}
	public static void main(String[] s){
		int[][] mtx={{1,2,3,4},
					 {5,9,2,5},
					 {1,5,3,9},
					 {8,6,3,2},
					 {9,8,6,5}};
		printSpiral(mtx);
		System.out.println();
		spiralPrint(mtx,0,0,5,4);
	}
}
