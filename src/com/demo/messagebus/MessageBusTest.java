package com.demo.messagebus;

import java.util.HashMap;
import java.util.Map;

public class MessageBusTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MessageBus.addClient("topic1", new Client1());
		MessageBus.addClient("topic2", new Client2());
		MessageBus.addClient("topic2", new Client1());
		Map<String,String> m = new HashMap<String,String>();
		m.put("firstName", "Rajeev");
		m.put("lastName", "Kumar");
		Message msg = new Message("topic1",m);
		Message msg1 = new Message("topic2",m);
		MessageBus.addMessage(msg);
		MessageBus.addMessage(msg1);
		m.put("Company", "Groupon");
		Message msg2 = new Message("topic2",m);
		Message msg3 = new Message("topic2",m);
		MessageBus.addMessage(msg2);
		MessageBus.addMessage(msg3);
	}

}
