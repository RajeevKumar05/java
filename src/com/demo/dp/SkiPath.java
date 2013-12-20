package com.demo.dp;

public class SkiPath {
	/*private static Integer[][] peaks = {{7,2,3,4,5},
									{36,37,38,34,6},
									{33,44,46,40,7},
									{24,43,42,41,8},
									{35,32,47,30,9}}; */
	
	private static Integer[][] peaks = {
		{56, 14, 51, 58, 88},
		{26, 94, 24, 39, 41},
		{24, 16, 8, 51, 51},
		{76, 72, 77, 43, 10},
		{38, 50, 59, 84, 81},
		{5, 23, 37, 71, 77},
		{96, 10, 93, 53, 82},
		{94, 15, 96, 69, 9},
		{74, 0, 62, 38, 96},
		{37, 54, 55, 82, 38} };
	
	private static Integer[][] lRun = new Integer[peaks.length][peaks[0].length];	
	public static int calLongestRun(int i,int j){
		int useLeft = -1;int useRight = -1;
		int useAbove = -1;int useBelow = -1;
		if(lRun[i][j] > 1)
			return lRun[i][j];
		
		if(isAllowed(i,j-1,i,j))
			useLeft = 1 + calLongestRun(i,j-1);
		if(isAllowed(i,j+1,i,j))
			useRight = 1 + calLongestRun(i,j+1);
		if(isAllowed(i-1,j,i,j))
			useAbove = 1 + calLongestRun(i-1,j);
		if(isAllowed(i+1,j,i,j))
			useBelow = 1 + calLongestRun(i+1,j);
		
		lRun[i][j] = max(useLeft,useRight,useAbove,useBelow);
		return lRun[i][j];
	}
	
	public static int max(int lr,int rr,int ar,int br){
		int m=1;
		if(lr > m)
			m = lr;
		if(rr > m)
			m = rr;
		if(ar > m)
			m = ar;
		if(br > m)
			m = br;
		return m;
	}
	
	public static boolean isAllowed(int x1,int y1,int x,int y){
		boolean ret = false;
		if(x1 >= 0 && y1 >= 0 && x1 < peaks.length && y1 < peaks[0].length){
			if(peaks[x1][y1] < peaks[x][y])
				ret = true;
		}
		return ret;
	}
	
	public static void main(String[] s){
//		System.out.println("ROW = "+lRun.length);
//		System.out.println("COL = "+lRun[0].length);
		
		for(int i = 0;i<lRun.length;i++){
			for(int j=0;j<lRun[0].length;j++){
				lRun[i][j] = 1;				
			}			
		}
		
		int longestRun = -1;
		int x = -1; int y = -1;int temp;
		for(int i=0;i<peaks.length;i++){
			for(int j=0;j<peaks[0].length;j++){
				temp = calLongestRun(i,j);
				if(temp > longestRun){
					longestRun = temp;
					x = i; y=j;
				}
			}
		}		
		System.out.println("Longest Run is : "+longestRun);
		System.out.println("Ending peak Cordinates: ("+x+","+y+")");
		
		
		for(int i = 0;i<lRun.length;i++){
			for(int j=0;j<lRun[0].length;j++){
				System.out.print(lRun[i][j]+"  ");				
			}
			System.out.println();
		}
	}
}
