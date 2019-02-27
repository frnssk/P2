package p2;

import java.net.Socket;
import java.io.*;

public class MessageProducerClient{
	private String ip;
	private int port;
	private Socket socket;
	private ObjectOutputStream oos;

	public MessageProducerClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void connect() throws IOException{
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
	}

	public void disconnect() throws IOException {
		socket.close();
	}

	public void send(MessageProducer messageProducer) {
		try {
			connect();
			oos.writeObject(messageProducer);
			oos.flush();
			disconnect();

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
