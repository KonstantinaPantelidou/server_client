import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
//mazi me dai16169

public class Main {

	private static final int PORT = 10000;
	private static ConcurrentHashMap<String, ArrayList<String>> flights = new ConcurrentHashMap<String, ArrayList<String>>();

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		ServerSocket connection = new ServerSocket(PORT);

		while (true) {
			System.out.println("O server pigainei sto port: " + PORT);
			Socket dataSocket = connection.accept();
			System.out.println("lamvanei aitima apo " + dataSocket.getInetAddress());

			ServerThread server = new ServerThread(dataSocket, flights);
			server.start();

		}
	}
}
