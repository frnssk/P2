package p2;

public class P1Viewer extends Viewer{
	
	/*
	 * Takes a MessageManager object and 2 int's as parameters
	 * Sends int's to super-class (viewer) as width and height
	 * adds MessageManager as callback
	 */
	public P1Viewer(MessageManager messageManager, int width, int height) {
		super(width, height);
		messageManager.addCallback(new UpdateMessage());
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
