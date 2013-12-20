package com.demo.test;

public class MaximumTreasure {
	public int max(int x,int y){
		return x>y?x:y;
	}
	public int maxValue(char[][] map,int i,int j){
		if(i<0 || i>=map.length || j<0 || j>=map[0].length)
			return 4;
		if(map[i][j]=='0')
			return 2;
		if(map[i][j]=='#')
			return 0;
		if(map[i][j]=='2')
			map[i][j]='#';
		if(map[i][j] == 'T'){
			map[i][j] = '0';
			this.printMap(map);
			int max = max(max(max(maxValue(map,i,j+1),maxValue(map,i,j-1)),maxValue(map,i-1,j)),maxValue(map,i+1,j)) + 12 -2;
			map[i][j] = 'T';
			this.printMap(map);
			return max;
		}else{
			map[i][j]='0';
			int m = max(max(max(maxValue(map,i,j+1),maxValue(map,i,j-1)),maxValue(map,i-1,j)),maxValue(map,i+1,j)) - 2;
			map[i][j]='1';
			return m;
		}
	}
	
	public void printMap(char[][] map){
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++)
				System.out.print(map[i][j]+" ");
			System.out.println();
		}
		System.out.println("-----------------------");
	}
	
	public static void main(String[] s){
		char[][] map = {{'T','1'},{'0','2'}};
		MaximumTreasure mt = new MaximumTreasure();
		mt.printMap(map);
		System.out.println(mt.maxValue(map, 1, 1));
		mt.printMap(map);
	}
}
