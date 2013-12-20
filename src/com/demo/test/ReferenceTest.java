package com.demo.test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {
	public static void main(String[] s) {
		ReferenceTest rt = new ReferenceTest();
		WeakReference<ReferenceTest> wr = new WeakReference<ReferenceTest>(rt);
		//SoftReference<ReferenceTest> sr = new SoftReference<ReferenceTest>(rt);
		rt = null;
		while(wr!=null){
			if(wr.get()!=null){
				System.out.println("Weak Reference Still Present !!");
			}else{
				System.out.println("Weak Referenced Object Garbage Collected !!");
				break;
			}
		}
		System.out.println("#############################");
		System.out.println("#############################");
		System.out.println("#############################");
		//Thread.sleep(5000);
		/*
		while(sr!=null){
			if(sr.get()!=null){
				System.out.println("Soft Reference Still Present !!");
			}else{
				System.out.println("Soft Referenced Object Garbage Collected !!");
				break;
			}
		}
		*/
	}
}
