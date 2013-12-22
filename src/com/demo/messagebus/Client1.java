package com.demo.messagebus;

import com.demo.messagebus.common.Message;

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
