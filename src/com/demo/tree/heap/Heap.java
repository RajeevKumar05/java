package com.demo.tree.heap;

import java.util.ArrayList;
import java.util.List;

public class Heap {
	private List<Integer> ha = new ArrayList<Integer>();
	private int size = 0;
	
	public void heapify(){
		int i = size-1;
		int p = (i-1)/2;
		int temp;
		while(p>=0 && ha.get(i)>ha.get(p)){
			temp = ha.get(i);
			ha.set(i, ha.get(p));
			ha.set(p, temp);
			i = p;
			p = (p-1)/2;
		}
	}
	public void add(int key){
		ha.add(key);
		size++;
		heapify();
	}
	public int maxidx(int x,int y){
		if(x<size){
			if(y<size){
				if(ha.get(x)>ha.get(y))
					return x;
				else
					return y;
			}else{
				return x;
			}
		}else{
			return -1;
		}
	}
	public void reHeapify(int p){		
		int bigger_idx = maxidx(2*p+1,2*p+2);
		if(bigger_idx>0 && ha.get(bigger_idx)>ha.get(p)){
			int temp = ha.get(p);
			ha.set(p, ha.get(bigger_idx));
			ha.set(bigger_idx, temp);
			p=bigger_idx;
			reHeapify(p);
		}
	}
	public Integer remove(){
		//int l = ha.size();
		int ret;;
		if(size>0){
			ret = ha.get(0);
			int t = ha.remove(size-1);
			size--;
			//System.out.println("Heap Size = "+ha.size());
			if(size>0){
				ha.set(0, t);
				reHeapify(0);
			}
			return ret;
		}
		System.out.print("Heap is empty, Please add elements : ");
		return null;
	}
	
	public void inplaceSort(int[] a){
		
	}
	
	public static void main(String[] s){
		Heap H = new Heap();
		H.add(2);H.add(5);H.add(-3);H.add(11);H.add(9);
		System.out.println(H.remove());
		System.out.println(H.remove());
		System.out.println(H.remove());
		System.out.println(H.remove());
		System.out.println(H.remove());
		System.out.println(H.remove());
		System.out.println(H.remove());
		System.out.println(H.remove());
		System.out.println(H.remove());
	}

}
