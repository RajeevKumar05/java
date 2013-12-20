package com.demo.reflection;

import java.lang.reflect.Method;
import com.demo.designpatterns.generic.Area;
import com.demo.designpatterns.generic.Circle;

public class TestReflection {
	public void showEntities(String[] classes) throws ClassNotFoundException{
		Class<?> c;
		String name;
		Method[] ms;
		for(int i=0;i<classes.length;i++){
			c = Class.forName(classes[i]);
			name = c.getCanonicalName();
			ms = c.getDeclaredMethods();
			System.out.println(name);
			for(int j=0;j<ms.length;j++){
				System.out.println("    "+j+" - "+ms[j]);
			}
		}
	}
	
	public static void main(String[] s) throws ClassNotFoundException, InterruptedException{
//		String[] classes = {"com.demo.designpatterns.generic.Area","com.demo.designpatterns.generic.Circle"};
//		TestReflection tr = new TestReflection();
//		tr.showEntities(classes);
		
		String s1 = new String("ABC");
		String s2 = new String("ABC");
		System.out.println(s1.equals(s2));
		s2.replace("A", "B");
		System.out.println(s1.equals(s2));
		s2 = "ABB";
		System.out.println(s1.equals(s2));
		s2 = s2.replace("A", "B");
		System.out.println(s1.equals(s2));
		Circle c1 = new Circle(1);
		synchronized(c1){
			c1.wait();
		}		
		Circle c2 = c1;		
		System.out.println(c1.equals(c2));
	}
}
