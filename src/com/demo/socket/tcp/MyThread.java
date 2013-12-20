package com.demo.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MyThread extends Thread {
	Socket clientSocket;
	Thread t;
	PrintWriter out;
	BufferedReader in;
	public MyThread(Socket skt){
		this.clientSocket = skt;
		t = new Thread(this);		
	}
	public void run(){
		try{
			 out = new PrintWriter(clientSocket.getOutputStream(), true);
		     in = new BufferedReader(
						new InputStreamReader(
						clientSocket.getInputStream()));
		        String inputLine, outputLine;
		        //KnockKnockProtocol kkp = new KnockKnockProtocol();

		        outputLine = "Connected to server.";
		        out.println(outputLine);

		        while ((inputLine = in.readLine()) != null) {
		        	if (inputLine.equalsIgnoreCase("Bye")){
		        		out.println("Bye.");
		        		break;
		        	}
		             outputLine = "SERVER:"+inputLine;
		             out.println(outputLine);
		             
		        }		        		
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			out.close();
	        try{
	        	in.close();
		        clientSocket.close();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
		}	
	}
}
