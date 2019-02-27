package p2;

//Interface for MessageProdducer 
public interface MessageProducer {
	public int delay();
	public int times();
	public int size();
	public Message nextMessage();
	
	//prints the info from the variables 
	default void info() {
		System.out.println("times="+times()+", delay="+delay()+", size="+size()+"]");
	}
}
