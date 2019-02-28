package p2;

import java.util.LinkedList;

//Reads a buffer with Message-objects and send to observing classes 
public class MessageManager {
	private Buffer<Message> buffer;
	private Thread thread;
	private LinkedList<Callback> list = new LinkedList<Callback>();

	public MessageManager(Buffer<Message> buffer) {
		this.buffer = buffer;
	}
	
	public void addCallback(Callback callback) {
		list.add(callback);
	}

	/*
	 * Checks if thread is active
	 * If not, starts a new thread from inner class
	 */
	public void start() {
		if(thread==null) {
			thread = new InnerThread();
			thread.start(); 
		}
	}

	/*
	 * Inner class that reads Message-objects from buffer
	 * Updates all objects waiting for the callback
	 */
	private class InnerThread extends Thread {
		public void run() {
			while(!Thread.interrupted() ) {
				try {
					Message message = buffer.get();
					for(int i = 0; i < list.size(); i++) {
						list.get(i).update(message);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				thread = null; //Makes thread empty so it can be started again 
			}
		}
	}

}
