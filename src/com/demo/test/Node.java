package com.demo.test;

public class Node {
	int data;
	Node next;
	
	public Node(int d){
		this.data = d;
		this.next = null;
	}
	
	public void heapify(Node root,boolean isMax){		
		if(isMax){
			maxHeapify(root);
		}
	}
	public int compare(Node root, int idx, Node cNode){
		while(idx>0){
			root = root.next;
			idx--;
		}
		if(root.data > cNode.data)
			return 1;
		else if(root.data < cNode.data)
			return -1;
		else
			return 0;
	}
	public void shiftUp(Node root,int idx,Node cNode){		
		if(compare(root,idx,cNode)<=0){
			Node tRoot = root;
			int tidx = idx;
			while(idx>0){
				tRoot = tRoot.next;
				idx--;
			}
			int temp = tRoot.data;
			tRoot.data = cNode.data;
			cNode.data = temp;
			//System.out.println("tidx = "+tidx);
			if(tidx > 0)
				shiftUp(root,(tidx-1)/2,tRoot);
		}
	}
	public void maxHeapify(Node root){
		if(root == null)
			return;
		Node tRoot = root.next;
		int idx = 1;
		while(tRoot != null){
			shiftUp(root,(idx-1)/2,tRoot);
			idx++;
			tRoot = tRoot.next;
		}	
	}
	
	public void print(Node root){
		while(root != null){
			System.out.print(root.data + " ");
			root = root.next;
		}
		System.out.println("NULL");
	}
	public int swapIndex(Node root,int idx){
		int nidx = 2*idx +1;
		Node cNode = root;
		while(root != null && idx < nidx){
			root = root.next;
			idx++;
		}
		if(root == null)
			return -1;
		Node next = root.next;
		if(next == null){
			if(root.data > cNode.data)
				return idx;
			else
				return -1;
		}else if(root.data > next.data && root.data > cNode.data)
			return idx;
		else if(root.data <= next.data && next.data > cNode.data)
			return idx+1;
		else
			return -1;			
	}
	public void shiftDown(Node root,int idx){
		int sidx = swapIndex(root,idx);
		Node cNode = root;
		if(sidx>0){
			while(idx < sidx){
				root = root.next;
				idx++;
			}
			int temp = root.data;
			root.data = cNode.data;
			cNode.data = temp;
			shiftDown(root,idx);
		}
	}
	
	public int delete(Node root){
		int ret=0;
		if(root!=null){
			ret=root.data;
			if(root.next == null){
				root = null;
			}else{
				Node last = root.next;
				Node sLast = root;
				while(last.next != null){
					sLast = last;
					last = last.next;
				}
				root.data = last.data;
				sLast.next = null;
				shiftDown(root,0);
			}			
		}
		return ret;
	}
	
	public static void main(String[] s){
		Node root = new Node(3);
		root.next = new Node(6);
		root.next.next = new Node(2);
		root.next.next.next = new Node(5);
		root.next.next.next.next = new Node(10);
		root.print(root);
		root.heapify(root, true);
		root.print(root);
		System.out.println(root.delete(root));
		root.print(root);
		
	}

}
