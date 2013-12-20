package com.demo.tree;

import java.io.IOException;

public class Trie {
	private char data = '#';
	private Trie[] next = new Trie[26];
	private boolean isWord = false;
	
	public boolean add(char[] w){
		boolean ret = false;
		if(w.length == 0)
			return false;
		Trie cur = this;
		for(int i=0;i<w.length;i++){			
			if(cur.data == '#'){
				cur.data=w[i];
				if(!ret)
					ret = true;
			}
			if(i!=w.length-1){
				int idx = w[i+1]-'a'<0?w[i+1]-'A':w[i+1]-'a';
				if(idx<0 || idx > 25){
					//System.out.println("Invalid char : "+w[i+1]);
					return false;
				}
					
				if(cur.next[idx] == null){
					cur.next[idx] = new Trie();					
				}
				cur = cur.next[idx];
			}
		}
		if(ret){
			cur.isWord = true;
		}
		return ret;
	}
	
	public boolean isPresent(char[] w){
		Trie t = this;
		for(int i=0;i<w.length;i++){
			if(t == null)
				return false;
			if(t.data - 'a' != w[i] - 'a' && t.data - 'A' != w[i]-'a' &&
					t.data - 'A' != w[i] - 'A' && t.data - 'a' != w[i] - 'A')
				return false;
			System.out.println(w[i]+", FOUND");
			if(i<w.length-1){
				int idx = w[i+1]-'a'<0?w[i+1]-'A':w[i+1]-'a';
				if(idx<0 || idx > 25){
					System.out.println("Invalid char : "+w[i+1]);
					return false;
				}
				t = t.next[idx];				
			}
		}
		if(t.isWord)
			return true;
		else
			return false;
	}
	
	public static void main(String[] s) throws IOException{
		Trie t = new Trie();
		t.add("abc".toCharArray());
		t.add("abcd".toCharArray());
		System.out.println(t.isPresent("ab".toCharArray()));		
	}
}
