package com.demo.messagebus;

import java.util.List;

public class MessageHandler implements Runnable {
	Message msg;
	List<Client> clients;
	public MessageHandler(Message m, List<Client> clients){this.msg = m; this.clients = clients;}
	@Override
	public void run() {
		for(Client client : clients){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Pushing to Queue : "+client.name()+"\nPayload = "+this.msg.toString()+"\nTopic = "+this.msg.topic);
			System.out.println("-------------------------------------------");
		}
	}

}
