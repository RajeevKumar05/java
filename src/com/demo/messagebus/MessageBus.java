package com.demo.messagebus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageBus {
	
	public static HashMap<String,List<Client>> clientStore = new HashMap<String,List<Client>>();
	
	public static boolean addClient(String topic,Client cl){
		if(clientStore.get(topic) == null){
			List<Client> clnts = new ArrayList<Client>();
			clnts.add(cl);
			clientStore.put(topic, clnts);
		}else{
			clientStore.get(topic).add(cl);
		}
		return true;
	}
	
	public static boolean addMessage(Message m){
		MessageHandler mh = new MessageHandler(m,clientStore.get(m.topic));
		mh.run();
		return true;
	}
}
