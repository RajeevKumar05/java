package com.demo.tree.bst;

public class Node {
	private int data;
	private Node left;
	private Node right;
	private Node ios;
	
	public Node(){
		this.data = -1;
		this.left = null;
		this.right = null;
		this.ios = null;
	}
	
	public Node(int d){
		this.data = d;
		this.left = null;
		this.right = null;
		this.ios = null;
	}
	
	public Node(int d, Node lc, Node rc){
		this.data = d;
		this.left = lc;
		this.right = rc;
	}
	
	public static Node[] formNodes(int[] datas){
		Node[] nodes = new Node[datas.length];
		for(int i=0;i<datas.length;i++){
			nodes[i] = new Node(datas[i]);
		}
		return nodes;
	}
	
	public void setData(int d){
		this.data = d;
	}
	public void setRight(Node rc){
		this.right = rc;
	}
	public void setLeft(Node lc){
		this.left = lc;
	}
	public void setIOS(Node suc){
		this.ios = suc;
	}
	public void printData(){
		System.out.print(this.data+"-->");
	}
	public Node getRight(){
		return this.right;
	}
	public Node getLeft(){
		return this.left;
	}
	public int getData(){
		return this.data;
	}
	
	public Node getIOS(){
		return this.ios;
	}
	public boolean isLeaf(){
		if(this.left == null && this.right == null)
			return true;
		else
			return false;
	}
}
