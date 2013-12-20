package com.demo.messagebus;

public class Client1 implements Client {

	@Override
	public boolean push(Message m) {
		return true;
	}

	@Override
	public String name() {
		return "Client_1";
	}

}
