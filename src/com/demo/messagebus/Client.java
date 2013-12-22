package com.demo.messagebus;

import com.demo.messagebus.common.Message;

public interface Client {
	public boolean push(Message m);
	public String name();
}
