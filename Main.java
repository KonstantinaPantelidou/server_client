import java.io.IOException;
import java.net.Socket;
//mazi me dai16169

public class Main {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 10000;

	public static void main(String[] args) throws IOException {

		Socket dataSocket = new Socket(HOST, PORT);

		ClientThread client = new ClientThread(dataSocket);
		client.start();

	}
}
