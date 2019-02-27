package p2;

public class P1Viewer extends Viewer{
	private MessageManager messageManager;
	
	/*
	 * Takes a MessageManager object and 2 int's as parameters
	 * Sends int's to super-class (viewer) as width and height
	 * Sets MessageManager to the private variable messageManager
	 * Finally calls the action() method to start the inner thread in the MessageManager object 
	 */
	public P1Viewer(MessageManager messageManager, int width, int height) {
		super(width, height);
		this.messageManager = messageManager;
		messageManager.addCallback(new UpdateMessage());
		//action();
	}

	/*
	 * Inner class that observes MessageManager
	 * Sends Message object to super-method setMessage
	 */
	private class UpdateMessage implements Callback{
		public void update(Message message) {
			setMessage(message);
		}
	}

}
