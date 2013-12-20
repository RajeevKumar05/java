package com.demo.test;

import java.util.LinkedList;
import java.util.Queue;

public class CityTour {
	public int[] getAllStartingPoint(int[] p,int[] d){
		int[] r = new int[p.length];
		int i=0;
		int c_petrol = 0;
		int s = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		while(s < p.length){
			System.out.println("CP = "+c_petrol+", s = "+s+", i = "+i);
			while(c_petrol < 0){
				c_petrol = c_petrol - q.poll();
				s++;
			}			
			q.add(p[i]-d[i]);
			c_petrol = c_petrol + p[i]-d[i];
			i = (i + 1)%p.length;
			if(c_petrol >= 0 && i==s){
				System.out.println("Starting point : "+s);
				c_petrol = c_petrol - q.poll();
				System.out.println("CP = "+c_petrol+", i="+i);
				s++;
			}
		}		
		return r;
	}
	
	public static void main(String[] s){
		int[] p = {2,3,3,6};
		int[] d = {1,4,3,2};
		new CityTour().getAllStartingPoint(p, d);
	}
}
