package com.demo.test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


public class OrderedMapping {
	public void printWordsCount(String snts){
		SortedMap<String,Integer> tm = new TreeMap<String,Integer>(new Comparator(){
				public boolean equals(Object i){
					return this.equals(i);
				}
				public int compare(Object o1, Object o2) {
					// TODO Auto-generated method stub
					return o1.equals(o2)?0:1;
				}}
				);
		String[] arr = snts.split(" ");
		for(int i=0;i<arr.length;i++){
			if(tm.get(arr[i].trim()) == null)
				tm.put(arr[i].trim(), 1);
			else
				tm.put(arr[i].trim(), tm.get(arr[i].trim())+1);
		}
		
		Set<String> st = tm.keySet();
		Iterator<String> itr = st.iterator();
		String key;
		while(itr.hasNext()){
			key = itr.next();
			System.out.println(key + " = " + tm.get(key));
		}
	}
	
	public static void main(String[] s){
		OrderedMapping om = new OrderedMapping();
		om.printWordsCount("This is perfect, is not it.");
	}

}
