package com.demo.tree.bst;

public class DLLNode {
	private Node data;
	private DLLNode next;
	private DLLNode previous;
	
	public DLLNode(Node data){
		this.data = data;
		this.next = null;
		this.previous = null;
	}
	
	public void setNext(DLLNode node){
		this.next = node;
	}
	
	public void setPrevious(DLLNode node){
		this.previous = node;
	}
	
	public DLLNode getPrevious(){
		return this.previous;
	}
	
	public DLLNode getNext(){
		return this.next;
	}
	
	public Node getData(){
		return this.data;
	}
	
	public DLLNode getFirst(){
		DLLNode first = this;
		while(first.previous != null)
			first = first.previous;
		return first;
	}
}
