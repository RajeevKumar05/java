package com.demo.list;

public class LinkedList {
	Node head;
	Node tail1;
	public static Node nextHead;
	public static Node tail = null;
	public LinkedList(){}
	public LinkedList(int i){
		Node tmp = new Node(i);
		this.head = tmp;
		this.tail1 = tmp;
	}
	public void add(int i){
		Node n = new Node(i);
		this.tail1.setNext(n);
		this.tail1 = n;
	}
	
	public Node getHead(){
		return this.head;
	}
	public void setHead(Node n){
		this.head = n;
	}
	public Node reverse(Node cHead){
		if(cHead == null){
			return null;
		}else if(cHead.getNext() == null){
			return cHead;
		}
		else{
			reverse(cHead.getNext()).setNext(cHead);
			cHead.setNext(null);
			return cHead;
		}		
	}
	
	public void printList(){
		Node lHead = this.getHead();
		System.out.print((Integer)lHead.getKey());
		lHead = lHead.getNext();
		while(lHead != null){
			System.out.print("-->"+lHead.getKey());
			lHead = lHead.getNext();
		}
		System.out.println();
	}
	
	public int findLenth(){
		Node h = this.getHead();
		int count = 0;
		while(h != null){
			count++;
			h = h.getNext();
		}
		return count;
	}
	
	public Node get(int i){
		Node h = this.getHead();
		while(i>1){
			h = h.getNext();
			i--;
		}
		return h;
	}
	
	public static Node reverse(Node head,int k){
		if(head.getNext() == null){
			nextHead = null;
			tail = head;
			return head;
		}
		else if(k==1){
			nextHead = head.getNext();
			head.setNext(null);
			tail = head;			
			return head;
		}else{
			Node t = head.getNext();
			head.setNext(null);			
			Node tHead = reverse(t,k-1);			
			tail.setNext(head);			
			tail = head;
			return tHead;
		}
	}
	
	public static void main(String[] s){
		LinkedList list = new LinkedList(0);
		for(int i=1;i<5;i++){
			list.add(i);
		}
		list.printList();
		list.printList();
		
//		int length = list.findLenth();
//		Node last = list.get(length);
//		Node t = list.reverse(list.getHead());
//		list.setHead(last);
//		list.printList();
//		
//		double x = 5;
//		double y = 2;
//		double z = x/y;
//		System.out.println("DIV = "+z);
		LinkedList ll = new LinkedList();
		Node head = list.getHead();	
		nextHead = head;
		Node temp = null;
		Node tt=null;
		while(nextHead != null){
			head = reverse(nextHead,6);
//			tt=head;
//			while(tt != null){
//				System.out.print(tt.getKey().getData()+"->");
//				tt = tt.getNext();
//			}
			//System.out.println("Tail = "+tail.getKey().getData()+", Head = "+head.getKey().getData());
			if(temp == null){
				temp = tail;
				tt = head;
			}
			else{
				temp.setNext(head);
				temp = tail;
			}			
		}
		ll.setHead(tt);
		ll.printList();
	}
}
