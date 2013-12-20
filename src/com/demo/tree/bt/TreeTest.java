package com.demo.tree.bt;

public class TreeTest {
	
	public Node balancedTreeFromList(int[] d){
		if(d == null)
			return null;
		if(d.length == 0)
			return null;
		if(d.length == 1)
			return new Node(d[0]);
		int lh = d.length/2 -1;
		Node left = b_tree(d,0,lh);
		Node root = new Node(d[lh+1]);
		root.left = left;
		root.right = b_tree(d,lh+2,d.length-1);
		return root;
		
	}	
	public Node b_tree(int[] d,int s,int e){
		if(s>e)
			return null;
		if(s==e)
			return new Node(d[s]);
		int lh = (s+e)/2;
		Node left = b_tree(d,s,lh);
		Node root = new Node(d[lh+1]);
		root.left = left;
		root.right = b_tree(d,lh+2,e);
		return root;
		
	}
	
	public void preOrder(Node root){
		if(root != null){
			System.out.print(root.data+"  ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	public void inOrder(Node root){
		if(root != null){			
			inOrder(root.left);
			System.out.print(root.data+"  ");
			inOrder(root.right);
		}
	}
	
	public static void main(String[] s){
		int[] d = {1,2,3,4,5};
		TreeTest tt = new TreeTest();
		Node root = tt.balancedTreeFromList(d);
		tt.preOrder(root);
		System.out.println();
		tt.inOrder(root);
	}
}
