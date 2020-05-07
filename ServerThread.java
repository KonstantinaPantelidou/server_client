import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

class ServerThread extends Thread {

	private Socket dataSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ConcurrentHashMap<String, ArrayList<String>> flights;

	public ServerThread(Socket dataSocket, ConcurrentHashMap<String, ArrayList<String>> flights) {
		this.flights = flights;
		try {
			output = new ObjectOutputStream(dataSocket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(dataSocket.getInputStream());
		} catch (IOException e) {
			System.out.println("I/O Error " + e);
		}
	}

	public void run() {
		String inmsg = "";
		String outmsg;
		ServerProtocol app = new ServerProtocol(flights);

		try {
			do {
				inmsg = input.readUTF();
				outmsg = app.echo(inmsg);
				output.writeUTF(outmsg);
				output.flush();
			} while (!inmsg.equalsIgnoreCase("exit"));

			inmsg = input.readUTF();
			outmsg = app.write(inmsg);
			output.writeUTF(outmsg);
			output.flush();

			inmsg = input.readUTF();
			outmsg = app.read(inmsg);
			output.writeUTF(outmsg);
			output.flush();

			dataSocket.close();
		} catch (IOException e) {
			System.out.println("I/O Error " + e);
		}
	}
}
