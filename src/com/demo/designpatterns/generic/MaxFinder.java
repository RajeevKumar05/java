package com.demo.designpatterns.generic;

import java.util.ArrayList;

public class MaxFinder {
	public <T extends Comparable<T>,T1 extends ArrayList<Integer>> T findMax(T x,T y,T z,T1 t){
		T max = x;
		for(int i=0;i<t.size();i++)
			System.out.println("t["+(i+1)+"] = "+t.get(i));		
		if(y.compareTo(max)> 0)
			max = y;
		if(z.compareTo(max)>0)
			max = z;
		return max;
	}
	
	public static void main(String[] s){
		MaxFinder mf = new MaxFinder();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		Integer max = mf.findMax(8, 10, 5,list);
		System.out.println("Max of 8,10,5 is : "+max);
		System.out.println("Max of 1.0,-2.4,0.0 is : "+mf.findMax(1.0, -2.4, 0.0,list));
		System.out.println("Max of M,P,L is : "+mf.findMax('M', 'P', 'L',list));
		System.out.println(mf.findMax("Hello", "Mr.", "HDYD",list));
	}
}
