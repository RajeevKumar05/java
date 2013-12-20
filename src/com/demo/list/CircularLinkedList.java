package com.demo.list;

public class CircularLinkedList implements List {

	private Node _head;
	
	public CircularLinkedList(int key){
		Node head = new Node(key);
		head.setNext(head);
		this._head = head;
		
	}
	public void delete(int key) {
		Node start = this.getHead();
		if(start == null){
			System.out.println("List is empty");
			return;
		}
		Node temp = start;	
		while(temp.getNext() != start){
			if(temp.getNext().getKey() == key){
				Node front = temp.getNext().getNext();
				temp.getNext().setNext(null);
				temp.setNext(front);
				return;
			}
			temp = temp.getNext();
		}
		if(start.getKey() == (key)){
			if(start.getNext() == start)
				this._head = null;
			else{
				temp.setNext(start.getNext());
				this._head = start.getNext();
				start.setNext(null);
			}
		}
	}

	public boolean find(int key) {
		Node start = this.getHead();
		if(start == null){
			System.out.println("List is empty");
			return false;
		}
		if(start.getKey()==(key))
			return true;
		Node temp = start;
		while(temp.getNext() != start){
			if(temp.getKey()==(key))
				return true;
			temp = temp.getNext();
		}
		return false;
	}
	
	public void insert(int key){
		Node start = this.getHead();
		if(start == null){
			CircularLinkedList nList = new CircularLinkedList(key);
			this._head = nList.getHead();
			return;
		}
		Node temp = start;
		while(temp.getNext() != start)
			temp = temp.getNext();
		Node newNode = new Node(key);
		temp.setNext(newNode);
		temp.getNext().setNext(start);
	}

	public Node getHead(){
		return this._head;
	}
	
	public static void main(String[] s){
		int key = 9;
		CircularLinkedList clList = new CircularLinkedList(5);
		clList.delete(5);
		clList.print();
		clList.insert(key);
		clList.print();
		clList.insert(4);
		//clList.delete(new int(9));
		//key.setData(new Integer(999));
		clList.insert(92);
		System.out.println(clList.find(9));
		System.out.println(clList.find(key));
		
		Integer i1 = new Integer(22);
		Integer i2 = new Integer(22);
		if(i1 == i2)
			System.out.println("Equals");
		else
			System.out.println("Unequals");
		if(i1.equals(i2))
			System.out.println("Equals");
		else
			System.out.println("Unequals");
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
}
