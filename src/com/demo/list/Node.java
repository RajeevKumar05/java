package com.demo.list;

public class Node {
	private int key;
	private Node next;
	
	public Node(int val){
		this.key = val;
		this.next = null;
	}
	public void setNext(Node node){
		this.next = node;
	}
	public int getKey(){
		return this.key;
	}	
	public Node getNext(){
		return this.next;
	}
	public void setKey(int k){
		this.key = k;
	}
	public static Node parse(int[] a){
		if(a.length == 0)
			return null;
		Node head = new Node(a[0]);
		Node temp,pre=head;
		for(int i=1;i<a.length;i++){
			temp = new Node(a[i]);
			pre.next = temp;
			pre = temp;
		}
		return head;
	}
	
	public static void print(Node n){
		if(n==null)
			return;
		while(n.next !=null){
			System.out.print(n.key+"->");
			n = n.next;
		}
		System.out.println(n.key);
	}
	
	public int length(){
		if(this.next == null)
			return 1;
		return 1+this.next.length();
	}
}
