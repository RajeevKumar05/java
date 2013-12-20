package com.demo.socket.udp;
import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[2];
            byte[] sendData = new byte[1024];
            StringBuffer sb = new StringBuffer();
            String sentence;
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);                	 
            	  sentence = new String( receivePacket.getData());
            	  sb = sb.append(sentence);
//            	  serverSocket.receive(receivePacket);                	 
//            	  sentence = new String( receivePacket.getData());
//            	  sb = sb.append(sentence);
//                  while(true){
//                	  serverSocket.receive(receivePacket);                	 
//                	  sentence = new String( receivePacket.getData());
//                	  if(sentence.contains("$"))
//                	  	break;
//                	  else{
//                		  sb = sb.append(sentence);
//                	  }
//                	  	
//                  }                
                  sentence = sb.toString();
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  System.out.println("Host : "+IPAddress.getHostName());
                  System.out.println("Port :"+port);
                  String capitalizedSentence = sentence.toUpperCase();
                  sendData = capitalizedSentence.getBytes();
                  System.out.println("SIZE = "+sendData.length);
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
      }
}