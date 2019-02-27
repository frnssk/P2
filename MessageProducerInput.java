package p2;

/*
 * Takes a MessageProducer buffer in constructor
 * Uses one method to add MessageProducer objects in the buffer
 */
public class MessageProducerInput {
	private Buffer<MessageProducer> producerBuffer;
	
	//Takes a MessageProducer buffer as a parameter i constructor
	public MessageProducerInput(Buffer<MessageProducer> buffer) {
		this.producerBuffer = buffer;
	}

	//Takes a MessageProducer object as a parameter and adds to the buffer
	public void addMessageProducer(MessageProducer m) {
		producerBuffer.put(m);
	}
}
