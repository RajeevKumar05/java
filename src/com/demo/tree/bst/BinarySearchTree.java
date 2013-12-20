package com.demo.tree.bst;

public class BinarySearchTree {
	class Node{
		int key;
		public Node(){};
		public Node(int key){
			this.key = key;
		}
		public int getKey(){
			return this.key;
		}
		public void setKey(int key){
			this.key = key;
		}
	}
}
