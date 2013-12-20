package com.demo.socket.tcp;

import java.net.*;
import java.io.*;

public class MyServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        System.out.println("Server is running at port : 4444.");
        
        Socket clientSocket = null;
       while(true){
    	   try {
    		   System.out.println("Waiting for client.....");
               clientSocket = serverSocket.accept();
           } catch (IOException e) {
               System.err.println("Accept failed.");
               e.printStackTrace();
               //System.exit(1);
           }        
           System.out.println(clientSocket.getInetAddress().toString()+" Connected");
           //Runnable mwt = new MyWorkerThread(clientSocket);
           //mwt.run();           
           Thread mt = new MyThread(clientSocket);
           mt.start();
       }
      // serverSocket.close();
    }
}
