package com.demo.test;

public class Maze {
	private int[][] mtx;	
	private int rows;
	private int cols;
	public Maze(int[][] data){
		mtx = data;
		rows = data.length;
		//System.out.println("Rows = "+rows);
		cols = data[rows-1].length;
		//System.out.println("Cols = "+cols);		
	}
	public void printMaze(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++)
				System.out.print(mtx[i][j]);
			System.out.println();
		}
			
	}
	public boolean isPath(int r,int c){
		if(r>=0 && r < rows && c>=0 && c< cols && mtx[r][c]==1)
			return true;
		else
			return false;
	}
	public void print(char[] path,int idx){
		for(int i=0;i<idx;i++)
			System.out.print(path[i]);
		System.out.println();
	}
	public void printAllPaths(int r,int c,char[] p,int idx,int tr,int tc){
		if(r<0 || r>=rows || c<0 || c>= cols)
			return;
		if(mtx[r][c] == 0){			
			return;
		}
		if(r==tr && c==tc){
			print(p,idx);
			return;
		}
		int cell = mtx[r][c];
		mtx[r][c] = 0;
		p[idx] = 'W';
		printAllPaths(r,c-1,p,idx+1,tr,tc);
		p[idx] = 'N';
		printAllPaths(r-1,c,p,idx+1,tr,tc);
		p[idx] = 'E';
		printAllPaths(r,c+1,p,idx+1,tr,tc);
		p[idx] = 'S';
		printAllPaths(r+1,c,p,idx+1,tr,tc);
		mtx[r][c] = cell;
	}
	
	public static void main(String[] s){
		int[][] m = {{1,0,1,1},{1,1,1,1},{1,0,1,1}};
		Maze mz = new Maze(m);
		mz.printMaze();
		char[] p = new char[20];
		System.out.println();
		mz.printAllPaths(0, 0, p, 0, 0, 3);		
		System.out.println();
		mz.printMaze();
	}

}
