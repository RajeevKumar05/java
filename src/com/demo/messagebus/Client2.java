package com.demo.messagebus;

import com.demo.messagebus.common.Client;
import com.demo.messagebus.common.Message;

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
