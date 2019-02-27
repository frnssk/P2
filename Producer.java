package p2;

/**
 * The producer class takes 2 buffers and takes from one to put in the other 
 * @author fransisakeklund
 *
 */
public class Producer extends Thread{
	private Buffer<MessageProducer> producerBuffer;
	private Buffer<Message> messageBuffer;
	private Inner thread = new Inner();


	/**
	 * The main constructor takes a MessageProducer-buffer and a Message-buffer
	 * Instantiates the private attributes 
	 * @param prodBuffer A buffer of the type MessageProducer 
	 * @param messageBuffer A buffer of the type Message
	 */
	public Producer(Buffer<MessageProducer> prodBuffer, Buffer<Message> messageBuffer) {
		this.producerBuffer = prodBuffer;
		this.messageBuffer = messageBuffer;
	}

	/**
	 * Starts the thread 
	 */
	public void start() {
		thread.start();
	}

	/**
	 * Inner class that extends Thread.
	 * Uses a thread to get a Message object from the MessageProducer buffer
	 * Adds that message to the Message buffer
	 * @author fransisakeklund
	 *
	 */
	private class Inner extends Thread {

		public void run() {
			while( !Thread.interrupted() ) { //Thread will run as long as it's not interrupted 
				try {
					MessageProducer mp = producerBuffer.get(); //creates a new message instance to store locally before moving 
					Message[] message = new Message[mp.size()];

					//looping through the buffer, getting message objects and putting them in the Message buffer
					for(int i = 0; i < mp.times(); i++) {
						for(int j = 0; j < message.length; j++) {
							message[j] = mp.nextMessage();
							messageBuffer.put(message[j]);
							Thread.sleep(mp.delay());
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
