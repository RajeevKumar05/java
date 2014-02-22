package com.demo.messagebus.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import com.demo.messagebus.common.Constants;
import com.demo.messagebus.common.MBusQueue;
import com.demo.messagebus.common.MBusQueueFactory;
import com.demo.messagebus.common.Message;
import com.demo.messagebus.common.MessageBusProducer;

public class MBusOfflineClient {
	private String host = "localhost";
	private int port = 9673;
	
	public MBusOfflineClient(){
		this.createConnectionToMBusClientAndListen();
	}
	
	public MBusOfflineClient(String host,int port){
		this.host = host;
		this.port = port;
		this.createConnectionToMBusClientAndListen();
	}
	
	public void createConnectionToMBusClientAndListen(){
		Socket pipeToMBuseClient = null;
		BufferedReader in = null;
		MBusQueueFactory.createTopicQueue(System.getProperty(Constants.MESSAGEBUS_TOPIC));
		try {
			pipeToMBuseClient = new Socket(this.host, this.port);
			in = new BufferedReader(new InputStreamReader(pipeToMBuseClient.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: "+this.host);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "+this.host);
			System.exit(1);
		}

		StringBuilder fromServer = new StringBuilder();
		String line;
		try{
			System.out.println("************************************");
			fromServer = new StringBuilder();
			System.out.println("Waiting...");
			System.out.println("Fetching messages.....");
			Map<String,Object> m = new HashMap<String,Object>();
			m.put(Constants.MESSAGEBUS_TOPIC, System.getProperty(Constants.MESSAGEBUS_TOPIC));
			m.put(Constants.MESSAGEBUS_COMMAND, Constants.FETCH_MESSAGE);
			MessageBusProducer.sendToSocket(pipeToMBuseClient, new Message(m));
			Thread.sleep(2000);
			while ((line = in.readLine()) != null) {
				if (!line.equalsIgnoreCase("BYE")){
					fromServer.append(line);
				}else{
					System.out.println("************************************");
					System.out.println("Client received : "+fromServer.toString());
					Message msg = new Message(fromServer.toString());
					MBusQueue queue = MBusQueueFactory.getQueue(msg.topic());
					Message mm;
					if(queue != null){
						JSONArray messages = msg.getList(Constants.MESSAGE_LIST);
						for(int i=0;i<messages.length();i++){
							mm = new Message(messages.getJSONObject(i));
							System.out.println("Queuing in Message : "+mm.toString());
							queue.add(mm);
						}
					}
				}
			}
		}catch (JSONException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
				pipeToMBuseClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] s){
		System.setProperty(Constants.MESSAGEBUS_TOPIC, "my.test.topic");
		new MBusOfflineClient();
	}
}
