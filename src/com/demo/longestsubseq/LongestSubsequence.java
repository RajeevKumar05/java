package com.demo.longestsubseq;

public class LongestSubsequence {
	private static char[] a = "XYZTUVW".toCharArray();
	private static char[] b = "ABXYMUVW".toCharArray();
	private static Integer[][] l = new Integer[b.length][a.length];
	private static Integer[][] tl = new Integer[b.length][a.length];
	public static int tempLongest(int i,int j){
		if(i < 0 || j < 0)
			return 0;
		else if(tl[i][j] != null)
			return tl[i][j];
		else{
			if(b[i] == a[j]){
				tl[i][j] = 1 + tempLongest(i-1,j-1);				
			}else{
				tl[i][j] = 0;
			}
			return tl[i][j];
		}		
	}
	
	public static int longest(int i, int j){
		if(i < 0 || j < 0)
			return 0;
		else if(l[i][j] != null)
			return l[i][j];
		else{
			if(b[i] == a[j]){
				//System.out.print("E"+i+""+j);
				return (max(1 + tempLongest(i-1,j-1),longest(i-1,j-1)));
			}
			else
				return max(longest(i,j-1),longest(j,i-1));
		}
	}
	
	public static int max(int x,int y){
		if(x > y)
			return x;
		else 
			return y;
	}
	
	public static void main(String[] s){
		for(int i=0;i<b.length;i++){
			for(int j=0;j<a.length;j++){
				l[i][j] = longest(i,j);
				System.out.print("l["+i+"]["+j+"]="+l[i][j]+"  ");
			}
			System.out.println();
		}
		
		System.out.println("Length of maximum subsequence : "+l[b.length-1][a.length-1]);
	}
}
