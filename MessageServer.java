package p2;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class MessageServer implements Callback {
	private ServerSocket serverSocket;
	private LinkedList<ClientHandler> clientHandlerList = new LinkedList<ClientHandler>();

	/*
	 * Lissening for callback from MessageManager
	 * Connets to socket and starts inner class Connection
	 */
	public MessageServer(MessageManager messageManager, int port) {
		messageManager.addCallback(this);
		try { 
			serverSocket = new ServerSocket(port);
			new Connection().start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	//Gets Message-objects from callback, adds to LikedList
	public void update(Message message) {
		for(int i = 0; i < clientHandlerList.size(); i++) {
			clientHandlerList.get(i).newMessageToClient(message);
		}
	}

	/*
	 * Connects to socket, 
	 * starts a new thread for each client with an instance of ClientHandeler
	 */
	private class Connection extends Thread {
		public void run() {
			while(true) {
				try {
					Socket socket = serverSocket.accept();
					new ClientHandler(socket).start();
				} catch(IOException e) {
					e.printStackTrace();

				}
			}
		} 
	}

	/*
	 * Sends message object to client with OutputStream
	 */
	private class ClientHandler extends Thread {
		private Socket socket;
		private Message message;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			clientHandlerList.add(this);
		}

		public void newMessageToClient(Message message) {
			this.message = message;
		}

		public void run() {

			try( ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); ){
				while(true) {
					while(message == null) {
						Thread.sleep(1);
					}
					oos.writeObject(message);
					oos.flush();
					message = null;
				} 
			}catch(IOException | InterruptedException e) {e.printStackTrace();}
		}
	}


}

