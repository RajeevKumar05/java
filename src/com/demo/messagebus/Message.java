package com.demo.messagebus;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Message {
	String topic;
	Map<String,String> payload;
	
	public Message(String topic,Map<String,String> payload){
		this.topic = topic;
		this.payload = payload;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		Set<String> keys = this.payload.keySet();
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()){
			String key = itr.next();
			sb.append("\n	{ "+key+" : "+payload.get(key)+" },");
		}
		sb.append("\n}");
		return sb.toString();
	}
}
