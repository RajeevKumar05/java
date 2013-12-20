package com.demo.dp;

public class EditDistance {
	public int min(int x,int y){
		return x>y?y:x;
	}
	public int recursiveED(char[] s1,char[] s2,int n1,int n2){
		if(n1==0)
			return n2;
		if(n2==0)
			return n1;
		if(s1[n1-1]==s2[n2-1]){
			return recursiveED(s1,s2,n1-1,n2-1);
		}else{
			return min(min(recursiveED(s1,s2,n1-1,n2-1)+1,recursiveED(s1,s2,n1-1,n2)+1),recursiveED(s1,s2,n1,n2-1)+1);
		}
	}
	
	public int dpED(char[] s1,char[] s2,int n1,int n2){
		if(n1==0)
			return n2;
		if(n2==0)
			return n1;
		int[][] m = new int[n1][n2];
		for(int i=0;i<n1;i++){
			for(int j=0;j<n2;j++){
				if(i==0){
					m[i][j]=j;
				}else if(j==0){
					m[i][j]=i;
				}else{
					if(s1[i]==s2[j])
						m[i][j]=m[i-1][j-1];
					else
						m[i][j]=min(min(m[i-1][j-1]+1,m[i-1][j]+1),m[i][j-1]+1);
				}
			}
		}
		return m[n1-1][n2-1];
	}
	
	public static void main(String[] s){
		String s1 = "ABC";
		String s2 = "AXYHFHD";
		EditDistance ed = new EditDistance();
		System.out.println(ed.recursiveED(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
		System.out.println(ed.dpED(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
	}
}
