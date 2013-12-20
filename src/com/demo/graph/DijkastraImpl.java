package com.demo.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DijkastraImpl {
	private static final int inf = -111;
	private static Integer[][] graph ={{0,12,4,18},
										{4,0,inf,11},
										{12,9,0,1},
										{inf,5,8,0}};
	
	String[] nodes = {"C1","C2","C3","C4"};	
	//ArrayList<String> nodes = {"C1","C2","C3","C4"};
	Set<String> cities = new HashSet<String>(getArrayList(nodes));
	
	public static List<String> getArrayList(String[] strgs){
		List<String> list = new ArrayList<String>();
		for(String str : strgs){
			list.add(str);
		}
		return list;
	}
	
	public static void runDijkastra(Set<String> visited,Set<String> unvisited){
		
	}

}
