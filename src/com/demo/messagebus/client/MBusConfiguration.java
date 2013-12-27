package com.demo.messagebus.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import com.demo.messagebus.common.Constants;
import com.demo.messagebus.common.MBusQueueFactory;
import com.demo.messagebus.common.MBusWorkerFactory;

import com.demo.messagebus.common.Message;

public class MBusConfiguration {

	public static void registerOnlineClient(String _chost,int _cport) throws IOException{
		String host = "localhost";
		int port = 4444;
		int clientPort = _cport;
		String clientHost = _chost;
		
		String _host = System.getProperty(Constants.MESSAGEBUS_SERVER_HOST);
		host = _host != null ? _host : host ;
		String _port = System.getProperty(Constants.MESSAGEBUS_SERVER_PORT);
		port = _port != null ? Integer.parseInt(_port) : port ;
		
		Map<String,Object> mp = new HashMap<String,Object>();
		
		mp.put("isRegistration", "yes");
		mp.put("topic", "my.test.topic");
		mp.put(Constants.MESSAGEBUS_CLIENT_HOST, clientHost);
		mp.put(Constants.MESSAGEBUS_CLIENT_PORT, clientPort);
		
		Message message = new Message(mp);
		
		Socket mbusSocket = null;
		PrintWriter out = null;
		//BufferedReader in = null;
		try {
			mbusSocket = new Socket(host, port);
			out = new PrintWriter(mbusSocket.getOutputStream(), true);
			//in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: "+host+", port: "+port);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "+host+", port: "+port);
			System.exit(1);
		}
		InputStream is = new ByteArrayInputStream(message.toString().getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder msg = new StringBuilder();

		String line = br.readLine();
		while(line != null){
			msg.append(line);
			line = br.readLine();
		}
		out.println(msg);
		out.println("BYE");
		out.close();
		//in.close();
		br.close();
		mbusSocket.close();
		System.out.println("Local client registered in online mode !!");
	}
	
	public static void initializeClientWorker() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		MBusWorkerFactory.addWorker(System.getProperty(Constants.MESSAGEBUS_TOPIC), System.getProperty(Constants.MESSAGEBUS_CLIENT_WORKER));
	}
	
	public static void initializeClientQueue(){
		MBusQueueFactory.createTopicQueue(System.getProperty(Constants.MESSAGEBUS_TOPIC));
	}
	
	public static void initConfig(){
		System.setProperty(Constants.MESSAGEBUS_TOPIC, "my.test.topic");
		System.setProperty(Constants.MESSAGEBUS_CLIENT_WORKER, "com.demo.messagebus.client.MBusClient");
		System.setProperty(Constants.ONLINE_CLIENT,"TRUE");
	}

}
