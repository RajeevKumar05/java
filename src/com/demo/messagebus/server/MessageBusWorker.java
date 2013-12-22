package com.demo.messagebus.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;

import com.demo.messagebus.common.Message;

public class MessageBusWorker implements Runnable {
	Socket clientSocket;
	//PrintWriter out;
	BufferedReader in;
	public MessageBusWorker(Socket socket){
		this.clientSocket = socket;
	}
	public void run(){
		try{

			//out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()));
			String inputLine; 
			//String outputLine;
			StringBuilder input = new StringBuilder();

			//outputLine = "Connected to server.";
			//out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {

				if (inputLine.equalsIgnoreCase("BYE")){
					//out.println("BYE.");
					break;
				}
				input.append(inputLine);   
			}
			MessageBus.process(new Message(input.toString()));
			//outputLine = "RECEIVED:"+input.toString();
			//out.println(outputLine);
		}catch(IOException ioe){
			ioe.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			//out.close();
			try{
				in.close();
				clientSocket.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
