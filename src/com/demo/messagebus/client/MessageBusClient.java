package com.demo.messagebus.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.demo.messagebus.common.Constants;
import com.demo.messagebus.common.IMBusWorker;
import com.demo.messagebus.common.MBusQueue;
import com.demo.messagebus.common.MBusQueueFactory;
import com.demo.messagebus.common.MBusWorkerFactory;
import com.demo.messagebus.common.Message;
import com.demo.messagebus.common.MessageBusProducer;

public class MessageBusClient extends Thread{
	@Override
	public void run() {
		String isOnlineClient = System.getProperty(Constants.ONLINE_CLIENT);
		if(isOnlineClient != null && isOnlineClient.equalsIgnoreCase("TRUE")){
			startAndRegisterClientToMBusServer();
		}else{
			try {
				createConnectionToMBusServerAndListen();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void readAndProcess(Socket skt) throws IOException{
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						skt.getInputStream()));
		String inputLine; 
		StringBuilder input = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {

			if (inputLine.equalsIgnoreCase("BYE")){
				//break;
				processMessage(input.toString());
			}
			input.append(inputLine);   
		}
		in.close();
	}
	
	public static void processMessage(String input){
		System.out.println("************************************");
		System.out.println("Client received : "+input);
		Message msg = null;
		String topic = null;
		try {
			msg = new Message(input);
			topic = msg.topic();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MBusQueue queue = MBusQueueFactory.getQueue(topic);
		if(queue != null)
			queue.add(msg);
		System.out.println("************************************");
	}

	public void createConnectionToMBusServerAndListen() throws IOException, JSONException{
		Socket pipeToMBuseserver = null;
		BufferedReader in = null;
		String host = "localhost";
		int port = 4444;

		try {
			String _host = System.getProperty(Constants.MESSAGEBUS_SERVER_HOST);
			String _port = System.getProperty(Constants.MESSAGEBUS_SERVER_PORT);
			if(_host != null && !_host.equalsIgnoreCase("")){
				host = _host;
			}
			if(_port != null && !_port.equalsIgnoreCase("")){
				port = Integer.parseInt(_port);
			}
			pipeToMBuseserver = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(pipeToMBuseserver.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: "+host);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "+host);
			System.exit(1);
		}

		StringBuilder fromServer = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			if (!line.equals("BYE"))
				fromServer.append(line);
			else{
				System.out.println("************************************");
				System.out.println("Client received : "+fromServer.toString());
				Message msg = new Message(fromServer.toString());
				MBusQueue queue = MBusQueueFactory.getQueue(msg.topic());
				if(queue != null)
					queue.add(msg);
				System.out.println("************************************");
				fromServer = new StringBuilder();
			}
			Map<String,Object> m = new HashMap<String,Object>();
			m.put(Constants.MESSAGEBUS_TOPIC, System.getProperty(Constants.MESSAGEBUS_TOPIC));
			m.put(Constants.MESSAGEBUS_COMMAND, Constants.FETCH_MESSAGE);
			MessageBusProducer.writeToSocket(pipeToMBuseserver, new Message(m));
		}
		in.close();
		pipeToMBuseserver.close();
	}

	public void startAndRegisterClientToMBusServer(){
		int port = 9673;
		ServerSocket clientSocket = null;
		try {
			String _port = System.getProperty(Constants.MESSAGEBUS_CLIENT_PORT);
			if(_port != null)
				port = Integer.parseInt(_port);
			clientSocket = new ServerSocket(port);
			MBusConfiguration.registerOnlineClient("localhost",port);
		} catch (IOException e) {
			System.err.println("MBus client could not listen on port: "+port+".");
			System.exit(1);
		}
		System.out.println("MBus client is running at port : "+port+".");

		Socket serverSocket = null;
		while(true){
			try {
				System.out.println("Waiting for mbus server.....");
				serverSocket = clientSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				e.printStackTrace();
			}        
			System.out.println(serverSocket.getInetAddress().toString()+" Connected");           
			try {
				readAndProcess(serverSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void printProperties(){
		System.out.println(Constants.MESSAGEBUS_TOPIC+" = "+System.getProperty(Constants.MESSAGEBUS_TOPIC));
	}

	public static void main(String[] s) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		MBusConfiguration.initConfig();
		MBusConfiguration.initializeClientWorker();
		MBusConfiguration.initializeClientQueue();

		Thread clientThread = new Thread(new MessageBusClient());

		MBusQueue queue = MBusQueueFactory.getQueue(System.getProperty(Constants.MESSAGEBUS_TOPIC));

		IMBusWorker worker = MBusWorkerFactory.getWorker(System.getProperty(Constants.MESSAGEBUS_TOPIC));

		Thread workerThread = new Thread(new MBusClientWorker(queue,worker));
		clientThread.start();
		//System.out.println("Client started, starting worker...");
		workerThread.start();
		clientThread.join();
		workerThread.join();
	}

}
