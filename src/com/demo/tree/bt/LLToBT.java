package com.demo.tree.bt;

import java.util.LinkedList;

public class LLToBT {
	public static LL headRef;
	static class LL{
		private int data;
		private LL next;
		public LL(int d){
			this.data = d;
			this.next = null;
		}		
		public int size(){
			LL cur = this;
			int l = 0;
			while(cur != null){
				l++;
				cur = cur.next;
			}
			System.out.println("Size = "+l);
			return l;
		}
	}
	public static Node makeBT(LL head){		
		if(head == null)
			return null;
		else{
			headRef = head;
			return makeBTUtil(head.size());		
		}
	}	
	public static Node makeBTUtil(int n){
		if(n==0 || headRef == null)
			return null;
		Node l = makeBTUtil(n/2);
		//head = head.next;
		Node root = new Node(headRef.data);
		headRef=headRef.next;
		root.left = l;
		root.right = makeBTUtil(n-n/2-1);
		return root;
	}
	
	public static void printInorder(Node root){
		if(root == null)
			return;
		printInorder(root.left);
		System.out.print(root.data+"->");
		printInorder(root.right);
	}
	public static void main(String[] s){
		LL head = new LL(1);
		head.next = new LL(2);
		head.next.next = new LL(3);
		head.next.next.next = new LL(4);
		head.next.next.next.next = new LL(5);
		head.next.next.next.next.next = new LL(6);
		head.next.next.next.next.next.next = new LL(7);
		head.next.next.next.next.next.next.next = new LL(8);
		//headRef = head;
		Node root = makeBT(head);
		Node.printPreorder(root);
		System.out.println();
		printInorder(root);
		
	}
}
