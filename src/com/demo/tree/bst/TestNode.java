package com.demo.tree.bst;

public class TestNode {
	public static void main(String[] s){
		ThreadedNode tn = (ThreadedNode)new Node(1);
		ThreadedNode tn1 = (ThreadedNode)new Node(2);
		tn.setRight(tn1);
		tn1.setLeftThread(tn);						
	}
}
