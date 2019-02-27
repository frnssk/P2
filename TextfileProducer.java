package p2;

import java.io.*;

import javax.swing.ImageIcon;

/**
 * The TextfileProducer class takes a text file (.txt) and creates Message-objects 
 * @author fransisakeklund
 *
 */
public class TextfileProducer implements MessageProducer {
	private String file;
	private Message[] messages;
	private int delay = 0;
	private int times = 0;
	private int index = -1;
	
	/**
	 * Constructor gets a String with the search path to a file.
	 * Calls the start method
	 * @param filename a string with the search path to the file
	 */
	public TextfileProducer(String filename){
		this.file = filename;
		start();
	}
	
	/**
	 * Starts by finding the file using the given search path
	 * Reads the file and creates an array of message objects 
	 */
	public void start() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))){
			times = Integer.parseInt(br.readLine()); //First line in .txt doc gives number of times animation should loop
			delay = Integer.parseInt(br.readLine()); //Second line gives the delay in milliseconds 
			messages = new Message[Integer.parseInt(br.readLine())]; //Third line gives the amount of Message Objects to be created 
			for(int i = 0; i < messages.length; i++) {
				messages[i] = new Message(br.readLine(), new ImageIcon(br.readLine()));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the delay as an int
	 */
	@Override
	public int delay() {
		return delay;
	}

	/**
	 * Returns times as an int
	 */
	@Override
	public int times() {
		return times;
	}

	/**
	 * Returns the size as an int
	 */
	@Override
	public int size() {
		return (messages==null) ? 0 : messages.length;
	}

	/**
	 * Returns the next message object in the array
	 */
	@Override
	public Message nextMessage() {
		if(size()==0)
		    return null;
		index = (index+1) % messages.length;
		return messages[index];
	}
}
