package com.demo.messagebus;

public interface Client {
	public boolean push(Message m);
	public String name();
}
