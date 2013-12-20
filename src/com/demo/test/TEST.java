package com.demo.test;

import java.util.Stack;

public class TEST {
	
	public void slidingWindoe(int[] list,int k){
		int min=list[1],_min=999;
		int[] min_s = new int[k];
		int tmin = 999;
		if(k>=list.length){
			System.out.println(min);
			return;
		}
		for(int i=k-1;i>0;i--){
			if(list[i]<tmin){
				tmin=list[i];
				min_s[i]=min;
			}
		}
		int wsize = k;
		for(int i=1;i<wsize;i++){
			if(list[i]<min){
				min = list[i];
			}
			if(list[i]<_min){
				_min = list[i];
			}
		}
		System.out.println(min);
		for(int i=wsize;i<list.length;i++){
			
		}
	}
	static boolean isOperand(String c){
		if("+".equals(c) || "*".equals(c))
			return false;
		else
			return true;
	}
	
	public void print(int n){
		while(n>0){
			System.out.print("-");
			n--;
		}
		System.out.println();
	}
	
	public void printRuler(int n){
		if(n<=1)
			return;
		if(n==2){
			print(1);
			return;
		}
		printRuler(n-1);		
		//print(1);
		print(n-1);
		printRuler(n-1);
	}
	
	static int evaluate(String a) {
	    Stack<String> stk = new Stack<String>();
	    Stack<String> stk1 = new Stack<String>();
	    int size = a.length();
	    char[] exp = a.toCharArray();
	    String ch = "";
	    String op1="";
	    String op2="";
	    String op ="";	    
	    for(int i=0;i<size;i++){
	        if(exp[i] == ')'){
	        	ch = stk.pop();
	        	//System.out.println("Popped - "+ch);
	        	while(!"(".equals(ch)){
	        		if(isOperand(ch)){
	        			stk1.push(ch);
	        		}else{        			
        				op = ch;
        				while(!stk1.isEmpty()){
        					op2 = op2 + stk1.pop();
        				}	        			
	        		}
	        		ch = stk.pop();
	        		//System.out.println("Popped - "+ch);
	        	}
	        	
				while(!stk1.isEmpty()){
					op1 = op1 + stk1.pop();
				}
    		
	        	//System.out.println("op1 = "+op1);
	        	//System.out.println("op  = "+op);
	        	//System.out.println("op2 = "+op2);
	        	if(!"".equals(op)){
	        		int o2 = Integer.parseInt(op2);	            
		            int o1 = Integer.parseInt(op1);
		            int val = calValue(o1,op,o2);	            
		            stk.push(val+"");
		            //System.out.println("Pushed - "+val);
	        	}else{
	        		stk.push(op1);
	        		//System.out.println("Pushed1 - "+op1);
	        	}
	       
	            op1 = "";
	            op2 = "";
	            op = "";
	        }else if(exp[i] == ' '){	        	
	        }
	        else{
	        	stk.push(exp[i]+"");
	        	//System.out.println("Pushed - "+exp[i]);
	        }
	    }
	    while(!stk.isEmpty()){
	    	stk1.push(stk.pop());	    	
	    }
	    while(!stk1.isEmpty()){
	    	ch = stk1.pop();
	    	if(isOperand(ch)){
	    		if("".equals(op))
	    			op2 = op2 + ch;
	    		else
	    			op1 = op1 + ch;
	    	}else{
	    		op = ch;
	    	}
	    }
	    if("".equals(op))
	    	return Integer.parseInt(op2);
	    else{
	    	return calValue(Integer.parseInt(op2),op,Integer.parseInt(op1));
	    }
	}

	public static int calValue(int n1,String op,int n2){
	    if("+".equals(op))
	        return n1+n2;
	    if("-".equals(op))
	        return n1-n2;
	    if("*".equals(op))
	        return n1*n2;
	    if("/".equals(op))
	        return n1/n2;
	    return 0;
	}
	
	public static int createSegmentTree(int[] source,int[] st,int start,int end,int index){
		if(start==end){
			st[index]=source[start];
			return st[index];
		}
		int mid=start+(end-start)/2;
		st[index] = createSegmentTree(source,st,start,mid,2*index+1)+createSegmentTree(source,st,mid+1,end,2*index+2);
		return st[index];
	}
	
	public static int getRangeSumUtil(int[] st,int ts,int te,int s,int e,int idx){
		if(ts>=s && te<=e)
			return st[idx];
		if(s<ts || e>te || s>e){
			return 0;
		}
		int tmid = ts +(te-ts)/2;
		int mid;
		if(tmid<e)
			mid = tmid;
		else
			mid = e;
		return getRangeSumUtil(st,ts,tmid,s,mid,2*idx+1)+getRangeSumUtil(st,tmid+1,te,mid+1,e,2*idx+2);
	}
	
	public static int getRangeSum(int[] st,int start,int end,int n){
		if(start<0 || end>n){
			return -999;
		}
		return getRangeSumUtil(st,0,n,start,end,0);
	}
	
	public int noOfWays(int[] a,int idx,int s){
		if(s==0)
			return 1;
		if(s<0)
			return 0;
		if(idx>=a.length)
			return 0;
		return (noOfWays(a,idx+1,s-a[idx]) + noOfWays(a,idx+1,s+a[idx])+noOfWays(a,idx+1,s));
	}
	
	public int getFinalAmount(int ia,char[] seq){
		return getFinalAmountUtil(ia,seq,1,0);
	}
	public int getFinalAmountUtil(int ia,char[] sq,int bet,int idx){
		if(bet > ia)
			return ia;
		if(idx>=sq.length)
			return ia;
		if('W'==sq[idx]){
			return getFinalAmountUtil(ia+bet,sq,1,idx+1);
		}else{
			return getFinalAmountUtil(ia-bet,sq,2*bet,idx+1);
		}
	}
	
	public int myAtoi(char[] c){
		int result = 0;
		boolean nig = false;
		if(c.length <= 0)
			return 0;
		if('-' == c[0]){
			nig = true;
			for(int i=1;i<c.length;i++){
				result = result*10+(c[i]-'0');
			}
		}else{
			for(int i=0;i<c.length;i++){
				result = result*10+(c[i]-'0');
			}
		}		
		return nig?result*-1:result;
	}
	public static void main(String[] s){
		//System.out.println(evaluate("(21+3)*22"));
		//Ruler printing
		/*
		int n = 4;
		TEST t = new TEST();
		t.print(n);
		t.printRuler(n);		
		t.print(n);
		*/
		int[] src = {2,3,4,5,6};
		int[] st = new int[2*src.length-1];
		int sum = createSegmentTree(src,st,0,src.length-1,0);
		System.out.println("Sum = "+sum);
		for(int i=0;i<st.length;i++)
			System.out.println("i="+i+", Val="+st[i]);
		System.out.println(getRangeSum(st,0,0,4));
		
		TEST t = new TEST();
		int[] m = {3,4,2,3};
		System.out.println("No OF Ways : "+t.noOfWays(m,0,6));
		
		System.out.println("Final Amount : "+t.getFinalAmount(10,"WWLLW".toCharArray()));
		String num="-123";
		System.out.println(num + " = "+t.myAtoi(num.toCharArray()));
	}
}
