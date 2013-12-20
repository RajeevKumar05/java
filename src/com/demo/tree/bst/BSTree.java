package com.demo.tree.bst;

import java.util.Queue;
import java.util.Stack;

public class BSTree {
	private Node root;
	public BSTree(){
		this.root = null;
	}
	public BSTree(Node root){
		this.root = root;
	}
	public Node getRoot(){
		return this.root;
	}
	public void makeBinaryTree(Node key){
		if(this.root == null)
			this.root = key;
		else
			this.modifySubTree(this.root, key);		
	}
	public void makeBinaryTree(Node[] nodes){
		for(Node node : nodes){
			makeBinaryTree(node);
		}
	}
	
	public void modifySubTree(Node sTree,Node key){
		if(key.getData() >= sTree.getData()){
			System.out.println("Going to the right of "+sTree.getData());
			if(sTree.getRight() == null)
				sTree.setRight(key);
			else
				modifySubTree(sTree.getRight(),key);
		}
		else{
			System.out.println("Going to the left of "+sTree.getData());
			if(sTree.getLeft() == null)
				sTree.setLeft(key);
			else
				modifySubTree(sTree.getLeft(),key);
		}
	}
	
	public void traverseInorderly(){
		if(this.root == null)
			System.out.println("BST is not yet created.");
		else{
			inorderTraversal(this.root);
		}
	}
	private void inorderTraversal(Node root){
		if(root.getLeft() != null)
			inorderTraversal(root.getLeft());
		
		root.printData();
		
		if(root.getRight() != null)
			inorderTraversal(root.getRight());
	}
	
	public void recursivePreorder(Node node){
		if(node != null){
			node.printData();
			recursivePreorder(node.getLeft());
			recursivePreorder(node.getRight());
		}
	}
	
	public void nonRecursivePreorder(Node rootNode){
		Stack<Node> stk = new Stack<Node>();
		Node curNode = rootNode;
		stk.push(curNode);
		Node visited;
		while(!stk.isEmpty()){
			visited = stk.pop();
			visited.printData();
			if(visited.getRight() != null)
				stk.push(visited.getRight());
			if(visited.getLeft() != null)
				stk.push(visited.getLeft());			
		}
	}
	
	public void topToBottomTraversal(Node rNode){
		
	}
	
	public void updateInorderSuccessor(Node root){
		Stack<Node> inList = this.getInorderStack(root);
		Node pNode = null;
		Node nNode = null;
		pNode = inList.pop();
		System.out.println(pNode.getData());
		if(inList.isEmpty())
			System.out.println("Empty stack returned");
		while(!inList.isEmpty()){
			nNode = inList.pop();
			System.out.println(nNode.getData());
			pNode.setIOS(nNode);
			pNode = nNode;
		}
	}
	
	public Node getLeast(Node root){
		while(root.getLeft() != null){
			root = root.getLeft();
		}
		return root;
	}
	
	public Node getPredecessor(Node root){
		Node pred = null;
		return pred;
	}
	
	public Node getSuccessor(Node root){
		Node succ = null;
		return succ;
	}
	
	public Stack<Node> getInorderStack(Node root){		
		Stack<Node> inorderStack = new Stack<Node>();
		if(root.getRight()!=null){
			inorderStack.addAll(getInorderStack(root.getRight()));
		}
		inorderStack.push(root);
		if(root.getLeft()!=null){
			inorderStack.addAll(getInorderStack(root.getLeft()));
		}				
		return inorderStack;
	}
	
	public DLLNode makeDoubleLinkList(Node root){
		DLLNode left = null;
		DLLNode temp;
		DLLNode head = null;
		if(root.getLeft() != null)
			left = makeDoubleLinkList(root.getLeft());
		if(left != null){
			temp = new DLLNode(root);
			while(left.getNext() != null){
				left = left.getNext();
			}
			left.setNext(temp);
			temp.setPrevious(left);
			head  = temp;			 
		}else{
			head = new DLLNode(root);
		}
		if(root.getRight() != null){
			DLLNode tn = makeDoubleLinkList(root.getRight());
			head.setNext(tn);
			tn.setPrevious(head);
		}
		while(head.getPrevious() != null)
			head = head.getPrevious();
		return head;
	}
	
	public void nonRecInorder(Node h){
		Stack<Node> st = new Stack<Node>();
		Node t;
		st.push(h);
		while(!st.isEmpty()){
			t = st.pop();
			if(t.getRight() != null)
				st.push(t.getRight());
			
			if(t.getLeft() != null)
				st.push(t.getLeft());
		}
	}
	
	public int findNodesCount(Node hd){
		if(hd == null)
			return 0;
		else
			return (1+findNodesCount(hd.getLeft())+findNodesCount(hd.getRight()));
		
	}
	
	public void findK(Node p, int k) {
		  if(p==null || k < 0) return;
		  findK(p.getLeft(), k);
		  k--;
		  if(k == 0) { 
		    p.printData();
		    return;  
		  } 
		  findK(p.getRight(), k); 
		}
}
