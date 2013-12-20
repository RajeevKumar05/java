package com.demo.tree.bst;

public class ThreadedNode extends Node {
	private ThreadedNode rightThread;
	private ThreadedNode leftThread;
	
	public void setRightThread(ThreadedNode tn){
		this.rightThread = tn;
	}
	public void setLeftThread(ThreadedNode tn){
		this.leftThread = tn;
	}
	public ThreadedNode getRightThread(){
		return this.rightThread;
	}
	public ThreadedNode getLeftThread(){
		return this.leftThread;
	}
	
}
