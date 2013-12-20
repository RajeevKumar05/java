package com.demo.tree.bt;

public class MaxSumSubtree {
	static int maxSum=-999;
	static int cSum=0;
	static Node maxRoot;
	
	static boolean isBST = false;
	static int maxSize = -999;
	static int curSize=0;
	static Node maxBSTNode;
	
	static int h=0;
	
	static Node targetLeaf;
	
	static int catalanNumber = 0;
	
	static int decimal=0;
	public static void maxSumSubtree(Node root){
				
	}
	
	public static void maxSumUtil(Node root){
		if(root==null){
			return;
		}
		maxSumUtil(root.getLeft());
		int lSum = cSum;
		cSum=0;
		maxSumUtil(root.getRight());
		int rSum = cSum;
		int curSum = lSum+root.data+rSum;
		if(curSum>maxSum){
			maxSum=curSum;
			maxRoot=root;
			//System.out.println("Updating root, Max Sum="+maxSum+", root = "+maxRoot.data);
		}
		cSum=curSum;
	}
	public static boolean isBSTNode(Node root){
		boolean ret=true;
		if(root.left !=null && root.data < root.left.data)
			ret = false;
		if(root.right != null && root.data > root.right.data)
			ret = false;
		return ret;
	}
	public static void largestBST(Node root){
		if(root==null){
			isBST=true;
			curSize=0;
			return;
		}
		largestBST(root.left);
		boolean b=isBST;
		isBST=false;
		int lSize = curSize;
		curSize=0;
		largestBST(root.right);
		//System.out.println("Root : "+root.data+", LBST : "+b+", RBST : "+isBST);
		if(isBST && b && isBSTNode(root)){
			int ts = lSize+1+curSize;
			if(ts>maxSize){
				maxSize=ts;
				maxBSTNode=root;
				//System.out.println("Updating size and BST node.");
			}
			curSize=ts;
		}else{
			curSize=0;
			isBST=false;
		}
	}
	
	public static int diameter(Node root){
		if(root==null){
			h=0;
			return 0;
		}
		int ld = diameter(root.left);
		int lh=h;
		h=0;
		int rd = diameter(root.right);
		int rh=h;
		h=max(lh,rh)+1;
		return max(max(ld,rd),rh+lh+1);		
	}
	
	public static void printKDistNodes(Node root,int k){
		if(root == null || k<0)
			return;
		if(k==0)
			System.out.print(root.data+" ");
		printKDistNodes(root.left,k-1);
		printKDistNodes(root.right,k-1);
	}
	public static int kDistNodes(Node root,int k,int key){
		if(root == null)
			return -1;
		int kk;
		if(key>root.data){
			kk=kDistNodes(root.right,k,key);
			if(kk==0)
				System.out.print(root.data+" ");
			else
				printKDistNodes(root.left,kk-1);
			return kk-1;
		}
		else if(key < root.data){
			kk=kDistNodes(root.left,k,key);
			if(kk==0)
				System.out.print(root.data+" ");
			else
				printKDistNodes(root.right,kk-1);
			return kk-1;
		}
		else{
			printKDistNodes(root,k);
			return k-1;
		}			
	}
	
		
	public static boolean kSumPath(Node root,int sum){
		if(root==null && sum!=0)
			return false;
		if(sum==0){			
			return true;
		}
		if(kSumPath(root.left,sum-root.data)){
			System.out.print(root.data+" ");
			return true;
		}
		else if(kSumPath(root.right,sum-root.data)){
			System.out.print(root.data+" ");
			return true;
		}else{
			return false;
		}
	}
	
	public static int max(int x,int y){
		if(x>y)
			return x;
		else
			return y;
	}
	
	public static void printBrackets(int open,int close,int idx,char[] br){
		if(open==0 && close==0){
			for(int i=0;i<br.length;i++)
				System.out.print(br[i]);
			System.out.println();
			catalanNumber++;
			return;
		}
		if(open==close){
			br[idx]='(';			
			printBrackets(open-1,close,idx+1,br);
		}else if(open==0){
			while(close>0){
				br[idx]=')';
				idx++;
				close--;
			}
			printBrackets(open,close,idx,br);
		}else{
			br[idx]=')';
			printBrackets(open,close-1,idx+1,br);
			br[idx]='(';
			printBrackets(open-1,close,idx+1,br);
		}		
	}
	
	public static int findBitValue(int k,int n) throws Exception{
		if(n<1 || k<=0 || k > (int)Math.pow(2, n-1))
			throw new Exception("Cant not find bit");
		if(n==1 && n==1)
			return 0;
		if(k>(int)Math.pow(2, n-2))
			return findBitValue(k-(int)Math.pow(2, n-2),n-1)==0?1:0;
		else
			return findBitValue(k,n-1);		
	}
	
	public static int findDecimal(Node root,int dec){
		if(root==null)
			return dec;
		int ld=findDecimal(root.left,dec);
		int rd = 2*ld+root.data;
		return findDecimal(root.right,rd);		
	}
	
	/**
	 * @param s
	 * @throws Exception
	 */
	public static void main(String[] s) throws Exception{
		Node root = new Node(10);		
		root.left=new Node(4);
		root.right=new Node(30);;
		root.left.left=new Node(-4);
		root.left.right=new Node(5);		
		root.right.left=new Node(15);
		root.right.right=new Node(2);
		root.right.right.left=new Node(18);
		//maxSumUtil(root);
		//System.out.println("Max Sum = "+maxSum);
		//System.out.println("Max sum root : "+maxRoot.data);
		largestBST(root);
		System.out.println("Largest BST size = "+maxSize+", Root = "+maxBSTNode.data);
		System.out.println("Diameter = "+diameter(root));
		System.out.println("");
		kDistNodes(root,3,30);
		System.out.println();
		kSumPath(root,55);
		
		//Node.printPath(root, targetLeaf);
		String str = "aaaaaaaaaaaaaaaaabbbccc";
		char[] ca = str.toCharArray();
		int in=1;		
		int f=1;
		int k=0;
		for(int i=1;i<ca.length;i++){
			if(ca[i]==ca[i-1])
				f++;
			else{
				while(f!=0){
					k=k*10+f%10;
					f=f/10;
				}
				while(k!=0){
					ca[in]=(char)('0'+k%10);
					k=k/10;
					in++;
				}				
				ca[in]=ca[i];
				in=in+1;
				f=1;
			}
		}
		while(f!=0){
			k=k*10+f%10;
			f=f/10;
		}
		while(k!=0){
			ca[in]=(char)('0'+k%10);
			k=k/10;
			in++;
		}
		System.out.println();
		for(int i=0;i<in;i++)
			System.out.print(ca[i]);
		
		
		System.out.println();
		int k1=6;
		char[] ch = new char[2*k1];
		//printBrackets(k1,k1,0,ch);
		//System.out.println("Catalan Number = "+catalanNumber);
		/*
	 - Level 1: 0
     - Level 2: 01
     - Level 3: 0110
     - Level 4: 01101001
		 */
		System.out.println("BIT="+findBitValue(8,4));	
		
		Node broot = new Node(0);
		broot.left=new Node(1);
		broot.right=new Node(1);
		broot.right.right=new Node(0);		
		System.out.println("Decimal = "+findDecimal(broot,0));
	}
	
}
