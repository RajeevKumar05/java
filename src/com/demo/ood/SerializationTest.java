package com.demo.ood;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTest{
	public static void main(String[] s) throws IOException, ClassNotFoundException{	
		FileOutputStream fos = new FileOutputStream("objects.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Employee e1 = new Employee(1,"Rajeev");
		e1.print();
		Employee e2 = new Employee(2,"Rajneesh");
		e2.print();
		oos.writeObject(e1);
		oos.writeObject(e2);
		System.out.println();
		Address a1 = new Address("Hyderabad","AP");
		a1.print();
		Address a2 = new Address("Patna","Bihar");
		a2.print();
		oos.writeObject(a1);
		oos.writeObject(a2);
		oos.flush();
		oos.close();
		fos.close();
		
		System.out.println("----------------------------------------");
		FileInputStream fis = new FileInputStream("objects.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
//		Object o;
//		while(ois.available()>0){
//			o = ois.readObject();
//			if(o instanceof Employee)
//				((Employee)o).print();
//			else if(o instanceof Address)
//				((Address)o).print();
//			else
//				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%");				
//		}
		
		Employee ee1 = (Employee)ois.readObject();
		Employee ee2 = (Employee)ois.readObject();
		ee1.print();
		ee2.print();
		System.out.println();
		Address aa1 = (Address)ois.readObject();
		Address aa2 = (Address)ois.readObject();
		aa1.print();
		aa2.print();
		ois.close();
		fis.close();
	}
}
