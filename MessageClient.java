package p2;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class MessageClient {
	private Socket socket;
	private ObjectInputStream ois;
	private LinkedList<Callback> list = new LinkedList<Callback>();

	//Creates an instance of MessageClient and connects to given socket
	public MessageClient(String ip, int port) {
		try {
			socket = new Socket (ip, port);
			ois = new ObjectInputStream(socket.getInputStream());
		} catch(IOException e) {
			e.printStackTrace();
		}
		new Listener().start();
	}

	public void addCallback(Callback callback) {
		list.add(callback);
	}

	/*
	 * Reads the stream and gets a message object
	 * Updates all objects waiting for the callback
	 * Resets the message object to null and starts over
	 */
	private class Listener extends Thread {
		Message message;

		public void run() {
			while(!Thread.interrupted()) {
				try {
					while(true) {
						message = (Message) ois.readObject();
						for(int i = 0; i < list.size(); i++) {
							list.get(i).update(message); 
						}
						message = null; 
					}
				}catch(IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
