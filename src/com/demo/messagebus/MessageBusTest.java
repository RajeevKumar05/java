package com.demo.messagebus;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.demo.messagebus.common.Message;
import com.demo.messagebus.server.MessageBus;

public class MessageBusTest {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		MessageBus.addClient("topic1", new Client1());
		MessageBus.addClient("topic2", new Client2());
		MessageBus.addClient("topic2", new Client1());
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("firstName", "Rajeev");
		m.put("lastName", "Kumar");
		m.put("topic", "topic1");
		Message msg = new Message(m);
		m.put("topic", "topic2");
		Message msg1 = new Message(m);
		MessageBus.process(msg);
		MessageBus.process(msg1);
		m.put("Company", "Groupon");
		Message msg2 = new Message(m);
		Message msg3 = new Message(m);
		MessageBus.process(msg2);
		MessageBus.process(msg3);
	}

}
