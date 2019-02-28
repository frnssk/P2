package p2;

public class P2Viewer extends Viewer {
	
	/*
	 * Takes a MessageManager object and 2 int's as parameters
	 * Sends int's to super-class (viewer) as width and height
	 * adds MessageClient as callback
	 */
	public P2Viewer(MessageClient messageClient, int width, int heigth) {
		super(width, heigth);
		messageClient.addCallback(new UpdateMessage());
	}
	
	/*
	 * Inner class that waits for callback from MessageClient
	 * Sends object to super-method in viewer
	 */
	private class UpdateMessage implements Callback{
		public void update(Message message) {
			setMessage(message);
		}
	}

}
