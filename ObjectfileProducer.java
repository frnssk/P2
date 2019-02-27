package p2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

//The TextfileProducer class takes a data file (.dat) and creates Message-objects
public class ObjectfileProducer implements MessageProducer {
	private String file;
	private Message[] messages;
	private int delay = 0;
	private int times = 0;
	private int index = -1;

	/*
	 * Constructor gets a String with the search path to a file.
	 * Calls the start method
	 */
	public ObjectfileProducer(String filename) {
		this.file = filename;
		start();
	}

	/*
	 * Starts by finding the file using the given search path
	 * Reads the file and creates an array of message objects 
	 */
	public void start() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			times = ois.readInt();
			delay = ois.readInt();
			messages = new Message[ois.readInt()];
			for(int i = 0; i < messages.length; i++) {
				try {
					Message temp = (Message) ois.readObject();
					messages[i] = new Message(temp.getText(), temp.getIcon());
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//Returns the delay as an int 
	@Override
	public int delay() {
		return delay;
	}

	//Returns times as an int
	@Override
	public int times() {
		return times;
	}

	//Returns the size as an int
	@Override
	public int size() {
		return (messages==null) ? 0 : messages.length;
	}

	//Returns the next message object in the array
	@Override
	public Message nextMessage() {
		if(size()==0)
			return null;
		index = (index+1) % messages.length;
		return messages[index];
	}


}
