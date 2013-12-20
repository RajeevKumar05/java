package com.demo.tree.bst;

public class TestBinaryTree {
	public static void main(String[] s){
//		int data;
//		BinaryTree tree = new BinaryTree();		
//		for(int i=0;i<s.length;i++){
//			data = Integer.parseInt(s[i]);
//			System.out.println("Input Data = "+data);
//			tree.makeBinaryTree(new Node(data));
//		}
		int[] datas = {5,9,-2,8,10};
		Node[] nodes = Node.formNodes(datas);
		BSTree tree = new BSTree();
		tree.makeBinaryTree(nodes);		
		Node root = tree.getRoot();
//		tree.updateInorderSuccessor(root);
//		System.out.println("Printing tree using inorder successor link:");
//		root = tree.getLeast(root);
//		System.out.print(root.getData());
//		while(root.getIOS() != null){
//			System.out.print("==>"+root.getIOS().getData());
//			root = root.getIOS();
//		}
//		System.out.println("\nPrinting Binary Tree Inorder way");
		tree.traverseInorderly();
		System.out.println();
//		Node root = tree.getRoot();
//		System.out.println("Recursive Preorder Printing:-");
//		tree.recursivePreorder(root);
//		System.out.println("Non-Recursive Preorder Printing:-");
//		tree.nonRecursivePreorder(root);
		
//		DLLNode head = tree.makeDoubleLinkList(tree.getRoot());
//		while(head.getNext()!=null){
//			System.out.print(" | "+head.getData().getData()+" | <==>");
//			head = head.getNext();
//		}
//		System.out.println(" | "+head.getData().getData()+" | ");
		//System.out.println("Nodes count : "+tree.findNodesCount(root));
		//find 3rd smallest
		//t.printData();
		//if(lc > 2)
		tree.findK(root, 3);
	}
}
