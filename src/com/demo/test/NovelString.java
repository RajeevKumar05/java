package com.demo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovelString {
	public static boolean isNovel(String s){
		boolean ret = true;
		for(int i=0;i<s.length()-1;i++){
			if(!isKSingular(s,i)){
				ret = false;
				break;
			}
		}
		return ret;
	}

	public static boolean isKSingular(String s, int k){
		boolean ret=true;
		Map<String,String> m = new HashMap<String,String>();
		char[] chars = s.toCharArray();
		StringBuilder sb;
		for(int i=0;i+k+1<s.length();i++){
			sb = new StringBuilder();
			sb.append(chars[i]).append(chars[i+k+1]);
			if(m.get(sb.toString())!=null){
				ret=false;
				break;
			}
			m.put(sb.toString(), "PRESENT");			
		}		  
		return ret;
	}
	//Contains both version(i.e input from console and input from file)
	//Default console input mode is enable
	//Enter one string in each line and $$ to end input
	//To test in file mode uncoment the commented out portion and in the main method
	//If testing in file mode it expects novel.in file in same directory where this class is
	public static void main(String[] s) throws IOException{
		
		List<String> list = new ArrayList<String>();
		DataInputStream d = new DataInputStream(System.in);
		String line="";
		System.out.println("Enter Strings($$ to end input):-");
		while(true){
			line = d.readLine();
			if("$$".equals(line))
				break;
			list.add(line);			
		}

		for(int i=0;i<list.size();i++){
			if(isNovel(list.get(i)))
				System.out.println(list.get(i)+" is a Novel string");
			else
				System.out.println(list.get(i)+" is not a Novel string");
		}

		/*
		FileInputStream fstreamr = new FileInputStream("novel.in");		  
		DataInputStream in = new DataInputStream(fstreamr);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		File f = new File("novel.out");
		f.createNewFile();
		FileWriter fstreamw = new FileWriter("novel.out");
		BufferedWriter out = new BufferedWriter(fstreamw);
		String str=br.readLine();
		while(!"$$".equals(str)){
			if(isNovel(str))
				out.write(str+" is a Novel string\n");					
			else
				out.write(str+" is not a Novel string\n");
			str=br.readLine();
		}
		out.close();
		br.close();
		*/
	}

}
