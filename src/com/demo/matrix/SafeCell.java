package com.demo.matrix;
import com.demo.common.Util;;

public class SafeCell {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] m = {{1,0,1},{0,0,1},{1,1,1}};
		
		int[] row = {1,1,1};
		int[] col = {1,1,1};
		
		for(int i=0;i<row.length;i++){
			for(int j=0;j<col.length;j++){
				if(m[i][j] == 1 && row[i] == 1)
					row[i] = 0;
				else if(m[i][j] == 0 && col[j] == 1)
					col[j] = 0;
			}
		}
		Util.printList(row);
		Util.printList(col);
		

	}

}
