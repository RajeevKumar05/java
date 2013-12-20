package com.demo.matrix;

import com.demo.common.Util;

public class CellLocking {

	public static void lockCells(int[][] m){
		int[] cols = new int[m[0].length];
		int[] rows = new int[m.length];
		for(int i=0;i<m.length;i++){
			for(int j=0;j<m[0].length;j++){
				if(m[i][j] == 1){
					rows[i] = 1;
					cols[j] = 1;
				}
			}
		}
		Util.printList(rows);
		Util.printList(cols);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] mtx = {{1,0,0,1},
				{1,1,1,1},
				{0,0,0,1}
				};
		lockCells(mtx);

	}

}
