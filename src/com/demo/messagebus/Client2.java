package com.demo.messagebus;

public class Client2 implements Client {

	@Override
	public boolean push(Message m) {
		return true;
	}

	@Override
	public String name() {
		return "Client_2";
	}

}
