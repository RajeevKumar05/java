package com.demo.tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Dictionary {
	private Trie[] dict;
	public Dictionary(){
		dict = new Trie[26];
		for(int i=0;i<26;i++)
			dict[i]=new Trie();
	}
	public boolean addWord(char[] w){		
		if(w.length==0)
			return false;
		int idx = w[0]-'a'<0?w[0]-'A':w[0]-'a';
		if(idx<0 || idx > 25)
			return false;
		return this.dict[idx].add(w);		
	}
	
	public boolean isPresent(char[] w){
		if(w.length==0)
			return true;
		int idx = w[0]-'a'<0?w[0]-'A':w[0]-'a';
		if(idx<0 || idx > 25)
			return true;
		return this.dict[idx].isPresent(w);
	}
	
	public void prepareDictionary(String path) throws IOException{
		int wCount = 0;
		File f = new File(path);
		Reader r = new FileReader(f);
		BufferedReader br = new BufferedReader(r);
		String[] words;
		try{
			String line  = br.readLine();
			while(line != null){
				words = line.split("[.| |,|/|:|)|(|?]");
				for(String word:words){
					word = word.trim();					
					if(this.addWord(word.toCharArray())){
							wCount++;
							System.out.println(word);
					}
				}
				line = br.readLine();
			}	
		}finally{
			br.close();
			System.out.println("Total unique words = "+wCount);
		}
			
	}
	
	public static void main(String[] s) throws IOException{
		String file = "D:\\TEMP\\temp\\test.txt";
		Dictionary d = new Dictionary();
		d.prepareDictionary(file);
		System.out.println(d.isPresent("heLlo".toCharArray()));
		System.out.println(d.isPresent("are".toCharArray()));
		System.out.println(d.addWord("Hello".toCharArray()));
		System.out.println(d.addWord("Hel#lo".toCharArray()));
		System.out.println();
		System.out.println(d.addWord("hii".toCharArray()));
		System.out.println(d.isPresent("hi".toCharArray()));
		System.out.println(d.isPresent("hii".toCharArray()));
	}
}
