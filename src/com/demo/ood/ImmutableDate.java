package com.demo.ood;

import java.util.Date;

public final class ImmutableDate {
	private final Date d;
	private static final int i=10;
	public ImmutableDate(){
		d = new Date();		
	}
	public Date getDate(){
		return (Date)this.d.clone();
	}
	
	public static void main(String[] s){
		ImmutableDate id = new ImmutableDate();
		System.out.println(id.getDate().getDate());
		id.getDate().setDate(11);
		System.out.println(id.getDate().getDate());
		
		Date td = new Date();
		System.out.println(td.getDate());
		td.setDate(11);
		System.out.println(td.getDate());
	}
}
