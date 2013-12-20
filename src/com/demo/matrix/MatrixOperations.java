package com.demo.matrix;

public class MatrixOperations {
	public void printDiagonal(int[][] mtx){
		int row = mtx.length;
		int col = mtx[0].length;
		System.out.println("ROW = "+row+", COL= "+col);
		for(int i=0;i<row+col;i++){
			print(mtx,i,row,col);
		}		
	}
	public void print(int[][] mtx,int diag,int r,int c){
		int cr,cc;
		if(diag>=r){
			cr=r-1;
			cc=diag-r+1;
		}else{
			cr=diag;
			cc=0;
		}
		while(cr>=0 && cc<c){
			System.out.print(mtx[cr][cc]+"  ");
			cr--;
			cc++;
		}
		System.out.println();
	}
	
	public static void main(String[] s){
		int[][] mtx={{1,2,3},{4,5,6},{7,8,9}};
		MatrixOperations mo = new MatrixOperations();
		mo.printDiagonal(mtx);
	}
}
