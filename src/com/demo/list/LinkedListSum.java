package com.demo.list;

public class LinkedListSum {
	public static Node sumUtil(Node a,Node b,int al,int bl){
		if(al==0)
			return b;
		if(bl==0)
			return a;
		if(al==1 && bl==1){
			return new Node(a.getKey()+b.getKey());
		}
		if(al>bl){
			Node t = sumUtil(a.getNext(),b,al-1,bl);
			Node h = new Node(a.getKey()+t.getKey()/10);
			t.setKey(t.getKey()%10);
			h.setNext(t);
			return h;
		}else if(al<bl){
			Node t = sumUtil(a,b.getNext(),al,bl-1);
			Node h = new Node(b.getKey()+t.getKey()/10);
			t.setKey(t.getKey()%10);
			h.setNext(t);
			return h;
		}else{
			Node t = sumUtil(a.getNext(),b.getNext(),al-1,bl-1);
			Node h = new Node(a.getKey()+b.getKey()+t.getKey()/10);
			t.setKey(t.getKey()%10);
			h.setNext(t);
			return h;
		}
	}
	public static Node sum(Node a,Node b){
		if(a==null)
			return b;
		if(b==null)
			return a;
		return sumUtil(a,b,a.length(),b.length());
	}
	public static void main(String[] s){
		int[] a = {1,4};
		int[] b = {4,5,6};
		Node ah = Node.parse(a);
		Node bh = Node.parse(b);
		Node.print(ah);
		Node.print(bh);
		Node.print(sum(ah,bh));
	}
}
