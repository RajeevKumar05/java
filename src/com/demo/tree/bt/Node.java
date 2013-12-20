package com.demo.tree.bt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Node {
	int data;
	Node left;
	Node right;
	
	static class Ref{
		int data;		
	}
	public Node(int d){
		this.data = d;
	}
	public Node getLeft(){
		return this.left;
	}
	public Node getRight(){
		return this.right;
	}
	public Node setRight(Node rn){
		this.right = rn;
		return rn;
	}
	
	public Node setLeft(Node ln){
		this.left = ln;
		return ln;
	}
	
	public void makeTree(Node root,Node ln,Node rn){
		root.left = ln;
		root.right=rn;
	}
	public static int findDifference(Node root,boolean isEven){
		if(root == null)
			return 0;
		else if(isEven)
			return findDifference(root.left,false)+findDifference(root.right,false)+root.data;
		else
			return findDifference(root.left,true)+findDifference(root.right,true)-root.data;
	}
	
	public static boolean printPath(Node root,Node leaf){
		if(root==null)
			return false;
		else if(root == leaf || printPath(root.left,leaf) || printPath(root.right,leaf)){
			System.out.print(root.data+"->");
			return true;
		}else{
			//System.out.println("No Path exist.");
			return false;
		}
	}
	
	public static List<Node> getPath(Node root,int key){
		List<Node> path = new ArrayList<Node>();
		if(root == null)
			return path;
		if(root.data == key){
			path.add(root);
			return path;
		}
		List<Node> lPath = getPath(root.left,key);
		if(lPath.size() > 0){
			path = lPath;
			path.add(root);		
		}else{
			List<Node> rPath = getPath(root.right,key);
			if(rPath.size()>0){
				path = rPath;
				path.add(root);
			}
		}
		return path;
	}
	
	public static void printPreorder(Node root){
		if(root == null)
			return;
		System.out.print(root.data+"->");
		printPreorder(root.left);
		printPreorder(root.right);
	}
	
	public static Node bstFromPreorder(int[] pret, int strt, int end){
		if(strt>end)
			return null;
		Node root = new Node(pret[strt]);
		int mid = -1;
		if(strt == end)
			return root;
		for(int k=strt+1;k<=end;k++){
			if(pret[k]>pret[strt]){
				mid = k;
				break;
			}
		}
		root.setLeft(bstFromPreorder(pret,strt+1,mid-1));
		root.setRight(bstFromPreorder(pret,mid,end));
		return root;
	}
	
	public static Node btFromPreIn(int[] pre,int[] in, int pr, int istrt , int iend){
		Node root = null;
		if(iend < istrt)
			return null;
		//System.out.println("pridx = "+pr+", istrt = "+istrt+", iend = "+iend);
		root = new Node(pre[pr]);
		if(istrt == iend)
			return root;
		int mid = -1;
		for(int k=istrt;k<=iend;k++){
			if(in[k] == pre[pr]){
				mid = k;
				break;
			}				
		}
		//System.out.println("pridx = "+pr+", istrt = "+istrt+", iend = "+iend+", mid = "+mid);
		if(mid == istrt){
			//System.out.println("Root = "+pre[pr]+", Only Right child");			
			root.setRight(btFromPreIn(pre,in,pr+1,mid+1,iend));
			root.setLeft(null);			
		}else if(mid == iend){
			//System.out.println("Root = "+pre[pr]+", Only Left child");
			root.setLeft(btFromPreIn(pre,in,pr+1,istrt,mid-1));
			root.setRight(null);
		}else{
			int rt_root = -1;
			for(int k=pr+2;k<pre.length;k++){
				for(int m=mid+1;m<=iend;m++){
					if(pre[k] == in[m]){
						rt_root = k;
						break;
					}	
				}
				if(rt_root != -1)
					break;
			}
			//System.out.println("Root = "+pre[pr]+", Both left and right child");
			root.setLeft(btFromPreIn(pre,in,pr+1,istrt,mid-1));
			root.setRight(btFromPreIn(pre,in,rt_root,mid+1,iend));
		}		
		return root;
	}
	
	public int max(int x,int y){
		if(x>y)
			return x;
		else
			return y;
	}
	
	public static List<Node> longestPathToLeave(Node root){
		List<Node> path = new LinkedList<Node>();
		if(root == null)
			return path;		
		List<Node> leftPath = longestPathToLeave(root.left);
		List<Node> rightPath = longestPathToLeave(root.right);
		if(leftPath.size()>rightPath.size()){
			path = leftPath;
			path.add(root);
			return path;
		}
		path = rightPath;
		path.add(root);
		return path;
			
	}
	
	public static List<Node> longestWalkBetweenLeaves(Node root){
		List<Node> path = new LinkedList<Node>();
		if(root == null)
			return path;
		else{
			List<Node> left = longestPathToLeave(root.left);
			List<Node> right = longestPathToLeave(root.right);
			Node temp;
			int l = right.size()-1;
			for(int i=0;i<l/2;i++){
				temp = right.get(i);
				right.set(i,right.get(l-i));
				right.set(l-i, temp);
			}
			path = left;
			path.add(root);
			path.addAll(right);
			
			List<Node> leftWalk = longestWalkBetweenLeaves(root.left);
			List<Node> rightWalk = longestWalkBetweenLeaves(root.right);
			return maxWalk(leftWalk,path,rightWalk);
		}		
	}
	
	public static List<Node> maxWalk(List<Node> lWalk,List<Node> cWalk,List<Node> rWalk){
		if(lWalk.size() > cWalk.size() && lWalk.size() > rWalk.size())
			return lWalk;
		else if(rWalk.size() > cWalk.size() && rWalk.size() > lWalk.size())
			return rWalk;
		else
			return cWalk;
	}
	
	public static void printPath(List<Node> path){
		for(int i=0;i<=path.size()-2;i++){
			System.out.print(path.get(i).data+"->");
		}
		System.out.println(path.get(path.size()-1).data);
	}
	
	public static void printPath1(List<Integer> path){
		for(int i=0;i<=path.size()-2;i++){
			System.out.print(path.get(i)+"->");
		}
		System.out.println(path.get(path.size()-1));
	}
	
	public static void printAllPathToLeaf(Node root,List<Integer> path){
		if(root == null)
			return;
		if(root.left == null && root.right == null){
			path.add(root.data);
			printPath1(path);
		}else{
			List<Integer> p1 = new ArrayList<Integer>(path);
			List<Integer> p2 = new ArrayList<Integer>(path);
			p1.add(root.data);
			p2.add(root.data);
			printAllPathToLeaf(root.left,p1);
			printAllPathToLeaf(root.right,p2);
//			if(root.left != null){
//				System.out.print(root.data+"->");
//				printAllPathToLeaf(root.left);
//			}
//			if(root.right != null){
//				System.out.print(root.data+"->");
//				printAllPathToLeaf(root.right);
//			}			
		}
	}
	
	public static void testPointer(Integer x,String s1,StringBuilder s2,List<Object> list){
		x = new Integer("25");
		s1 = "RAM";
		s2 = s2.append("NULL");
		list.add("ABC");
	}
	
	
	public static void printLeftNRightViewOfBT(Node node) {
		  if (null == node) { return ; }
		  Queue<Node> queue = new LinkedList<Node>();
		  Node marker = new Node(-999);
		  queue.add(node);
		  queue.add(marker);
		   
		  //boolean wasMarker = false;
		  ArrayList<Node> leftViewList = new ArrayList<Node>();
		  ArrayList<Node> rightViewList = new ArrayList<Node>();
		  leftViewList.add(node);
		  //rightViewList.add(node);
		  while (!queue.isEmpty()) {
		   Node currentNode = queue.remove();
		   if (currentNode.equals(marker)) {
		    if (!queue.isEmpty()) {
		     leftViewList.add(queue.peek());
		     queue.add(marker);
		    }
		   }
		   if (!queue.isEmpty() && queue.peek().equals(marker)) {
		    rightViewList.add(currentNode);     
		   }
		   if (currentNode.left != null) {
		    queue.add(currentNode.left);
		   }
		   if (currentNode.right != null) {
		    queue.add(currentNode.right);
		   }
		  }
		  Iterator<Node> itLeft = leftViewList.iterator();
		  System.out.println("Printing left View: ");
		  while (itLeft.hasNext()) {
		   System.out.print(itLeft.next().data + " ");
		  }
		  System.out.println();
		   
		  System.out.println("Printing right view: ");
		  Iterator<Node> itRight = rightViewList.iterator();
		  while (itRight.hasNext()) {
		   System.out.print(itRight.next().data + " ");
		  }
		  System.out.println();
		 }
	
	public static void verticalSum(Node root,int hD,Map<Integer,Integer> m){
		if(root == null)
			return;
		if(m.get(hD)!= null){
			int oldValue = m.get(hD);
			m.put(hD, oldValue+root.data);
		}else{
			m.put(hD, root.data);
		}
		verticalSum(root.left,hD-1,m);
		verticalSum(root.right,hD+1,m);		
	}
	public static int firstLarger(int n,List<Integer> list){
		int ret = -999;
		for(int i=0;i<list.size();i++){
			if(list.get(i) > n){
				ret = list.get(i);
				list.set(i, n);
				break;
			}				
		}
		return ret;
	}
	public static void printKDistDown(Node root,int k){
		//System.out.println("Node : "+root.data+", Dist = "+k);
		if(root == null)
			return;
		if(k==0){
			System.out.print(root.data+" ");
			return;
		}
		printKDistDown(root.left,k-1);
		printKDistDown(root.right,k-1);
		
	}
	public static void printKDistNodes(int k,Node root,int key){
		List<Node> path = getPath(root,key);
		int size = path.size();
		System.out.println("Printing nodes, "+k+" distance away from "+key);
		Node next = path.get(0);
		printKDistDown(next,k);
		int count = k-1;
		Node cur;
		for(int i=1;i<size;i++){
			cur = path.get(i);
			if(count == 0){
				printKDistDown(cur,count);
				break;
			}
			else if(cur.left == next){
				printKDistDown(cur.right,count -1);
				count --;				
			}else{
				printKDistDown(cur.left,count -1);
				count --;
			}
			next=cur;
		}
	}
	public static int concat(int x,int y,List<Integer> list){
		//System.out.println("x = "+x+", y = "+y);
		StringBuilder sb = new StringBuilder();
		sb.append(x);
		sb.append(y);
		for(int i=0;i<list.size();i++){
			//System.out.println("list("+i+")="+list.get(i));
			sb.append(list.get(i));
		}
		return new Integer(sb.toString());
	}
	public static int nextHigerPermutation(int x){
		int r;
		int q;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(x != 0){
			r = x%10;
			q = firstLarger(r,list);
			if(q == -999){
				list.add(r);
				x = x/10;
			}else{
				return concat(x/10,q,list);
			}
		}
		System.out.println("No larger permutation is possible");
		return -999;
	}
	
	public static void testVar(Ref r){
		r.data++;
	}
	
	public static int maxSumPathToLeaf(Node root,List<Integer> path){
		if(root == null)
			return 0;
		int pathLength;
		List<Integer> lPath = new ArrayList<Integer>();
		List<Integer> rPath = new ArrayList<Integer>();
		int l = maxSumPathToLeaf(root.left,lPath);
		int r = maxSumPathToLeaf(root.right,rPath);
		path.add(root.data);
		if(l>r){
			pathLength = l + 1;
			path.addAll(lPath);
			return pathLength;
		}else{
			pathLength = r + 1;
			path.addAll(rPath);
			return pathLength;
		}
	}
	
	public static void printMaxSumPath(Node root){
		int MIN = -999;
		Ref r = new Ref();
		r.data = MIN;
		Node target=maxSumPathUtil(root,r,0);
		System.out.println("Target is "+target.data);
		printPath(root,target);		
	}
	
	public static Node maxSumPathUtil(Node root,Ref r,int c_sum){
		if(root==null){
			r.data=-999;
			return null;
		}
		int current_sum=c_sum+root.data;
		if(root.left == null && root.right==null){
			if(current_sum > r.data){				
				r.data = current_sum;				
				return root;				
			}
		}
		int t=r.data;
		Node lt=maxSumPathUtil(root.left,r,current_sum);
		int ld = r.data;
		r.data=t;
		Node rt=maxSumPathUtil(root.right,r,current_sum);
		if(ld>r.data)
			return lt;
		else
			return rt;		
	}
	
	public static Node append(Node n1,Node n2){
		if(n1 == null && n2 != null)
			return n2;
		if(n1 != null && n2 == null)
			return n1;
		if(n1 == null && n2 == null)
			return null;
		Node t1 = n1.left;
		Node t2 = n2.left;
		t1.right = n2;
		n2.left = t1;
		t2.right = n1;
		n1.left = t2;
		return n1;
	}
	public static Node append(Node lh,Node ch,Node rh){
		ch.left = ch;
		ch.right = ch;
		return append(append(lh,ch),rh);
	}
	
	public static Node bstToDll(Node root){
		if(root == null)
			return null;
		else{			
			return append(bstToDll(root.left),root,bstToDll(root.right));
		}
	}
	
	public static void printDll(Node h){
		Node t = h;
		while(t.right != h){
			System.out.print(t.data+"<=>");
			t = t.right;
		}
		System.out.println(t.data);
	}
	
	public static boolean isFullBinaryTree(Node root){
		if(root == null)
			return true;
		if(root.left == null && root.right == null)
			return true;
		if(root.left == null || root.right == null)
			return false;
		return isFullBinaryTree(root.left) && isFullBinaryTree(root.right);
	}
	public static void main(String[] s){
		/*
		 * 								2
		 * 							   / \
		 * 							  /   \
		 * 							 5     9
		 * 								  / \
		 * 								 /   \
		 * 							   10     15
		 * 							  /		 /  \
		 * 							 /      /    \
		 * 						    16    20      25
		 * 							 \      \
		 * 							  \      \
		 * 							   4      30
		 */
		int[] pre = {5,3,2,4,8,6,15,10,19};		
		Node root = bstFromPreorder(pre,0,pre.length-1);
		System.out.println();
		printPreorder(root);
		System.out.println();
		int ppre[] = {1,5,2,8,3,9,10};
		int in[] = {2,5,8,1,9,3,10};
		
		int[] ppre1 = {2,5,9,10,16,4,15,20,30,25};
		int[] in1 =   {5,2,16,4,10,9,20,30,15,25};
		
		int[] p1 = {2,9,10};
		int[] i1 = {2,10,9};
		
		int[] p2 = {2,9,10};
		int[] i2 = {9,10,2};
		
		int[] p3 = {2,5,9,10};
		int[] i3 = {5,2,10,9};
		
		int[] p4 = {2,5,9,10};
		int[] i4 = {5,9,2,10};
		
		int[] p5 = {9,10,16,4,15};
		int[] i5 = {16,4,10,9,15};
		Node rroot = btFromPreIn(ppre1,in1,0,0,in1.length-1);
		//Node rroot = btFromPreIn(p1,i1,0,0,i1.length-1);
		Node root1 = btFromPreIn(p2,i2,0,0,i2.length-1);
		//Node rroot = btFromPreIn(p3,i3,0,0,i3.length-1);
		//Node rroot = btFromPreIn(p4,i4,0,0,i4.length-1);
		//Node rroot = btFromPreIn(p5,i5,0,0,i5.length-1);
//		printPreorder(rroot);
//		List<Node> _path = getPath(rroot,20);
//		System.out.println("Path from Root to 20 :");
		System.out.println();
		printKDistNodes(2,rroot,10);
		//printPath(_path);
		LinkedList<Node> lPath = (LinkedList<Node>)longestPathToLeave(root);
		LinkedList<Node> lWalk = (LinkedList<Node>)longestWalkBetweenLeaves(rroot);
		System.out.println();
		System.out.print("Longest Walk : ");
		printPath(lWalk);
		System.out.println();
		System.out.println("All path to leaves:-");
		List<Integer> path = new ArrayList<Integer>();
		printAllPathToLeaf(rroot,path);
		int t = 5;
		Integer t1 = new Integer(5);
		String s1 = new String("A");
		StringBuilder sb = new StringBuilder();
		List<Object> lis = new ArrayList<Object>();
		System.out.println("Old: t = "+t1+", s1 = "+s1+", sb = "+sb.toString()+", List size = "+lis.size());
		testPointer(t1,s1,sb,lis);
		System.out.println("New: t = "+t1+", s1 = "+s1+", sb = "+sb.toString()+", List size = "+lis.size());
		
		printLeftNRightViewOfBT(rroot);
		
		Map<Integer,Integer> vM = new HashMap<Integer,Integer>();
		verticalSum(rroot,0,vM);
		System.out.println("Vertical Sum");
		System.out.println(vM.entrySet());
		int num = 43986;
		System.out.println("Next higher permutation for "+num+" = "+nextHigerPermutation(num));
		List<Integer> leafPath = new ArrayList<Integer>();
		maxSumPathToLeaf(rroot,leafPath);
		System.out.println("Max Sum path to Leaf :- ");
		printPath1(leafPath);
		System.out.println("Max Sum path to Leaf :- ");
		printMaxSumPath(rroot);
		//Node nd = new Node(4);
//		Ref r = new Ref();
//		r.data=4;
//		testVar(r);
//		System.out.println("New value = "+r.data);
		
		
		/*
		Queue<Node> q=new LinkedList<Node>();
		Node n = new Node(2);
		Node ln = new Node(5);
		Node rn = new Node(1);
		Node root = n;
		Node root1 = n;
		root.left = ln;
		root.right = rn;
		q.add(root.left);
		q.add(root.right);
		
		root = q.poll();
		ln=new Node(4);
		rn=new Node(9);
		root.left=ln;
		root.right=rn;
		q.add(root.right);
		
		root=q.poll();
		ln = new Node(3);
		rn = new Node(6);
		root.left=ln;
		root.right=rn;
		
		root=q.poll();
		root.left=new Node(12);
		root.right = new Node(8);
		printPath(root1,root.right);
		System.out.println();
		printPath(root1,root.left);
		System.out.println();
		printPath(root1,ln);
		System.out.println();
		printPath(root1,rn);
		System.out.println();
		System.out.println("Even - Odd = "+findDifference(root1,false));*/
		
		//List<Integer> l = new ArrayList<Integer>();
		//l.set(2, 5);
		System.out.println();
		Node nr = new Node(1);
		nr.right = new Node(2);
		nr.left = new Node(-1);
		Node dh = bstToDll(nr);
		printDll(dh);
		
		Node nnr = new Node(1);
		nnr.right = new Node(2);
		nnr.left = new Node(3);
		System.out.println("Full : "+isFullBinaryTree(nnr));
	}
}
