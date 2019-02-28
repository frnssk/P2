package p2;

import java.io.*;
import java.net.*;


public class MessageProducerServer implements Runnable {
	private MessageProducerInput messageProducerInput;
	private Thread server = new Thread(this);
	private ServerSocket serverSocket;
	private int port;
	
	public MessageProducerServer(MessageProducerInput messageProducerInput, int port) {
		this.messageProducerInput = messageProducerInput;
		this.port = port;
	}
	
	public void startServer() {
		try {
			serverSocket = new ServerSocket(port);
			server.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Reads an object from the Stream and updates MessageProducerInput
	 * Server running until stopped
	 */
	public void run() {
		MessageProducer messageProducer;
		
		System.out.println("Server started");
		while(true) {
			try (Socket socket = serverSocket.accept();
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); ){
				try {
					messageProducer = (MessageProducer) ois.readObject();
					messageProducerInput.addMessageProducer(messageProducer);
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
