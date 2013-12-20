package com.demo.test;

public class KnightTour {
	int[][] board;
	public KnightTour(int n){
		this.board = new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				this.board[i][i]=0;
	}
	public boolean isToured(){
		for(int i=0;i<this.board.length;i++)
			for(int j=0;j<this.board.length;j++){
				if(this.board[i][j]==0)
					return false;
			}
		return true;
	}
}
