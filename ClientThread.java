import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {

	private Socket dataSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public ClientThread(Socket socket) {
		dataSocket = socket;
		try {
			output = new ObjectOutputStream(dataSocket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(dataSocket.getInputStream());
		} catch (IOException e) {
			System.out.println("I/O Error " + e);
		}

	}

	public void run() {
		String outmsg = "";
		String inmsg, code, status, time;
		Scanner scanner = new Scanner(System.in);
		ClientProtocol app = new ClientProtocol();

		try {
			do {
				System.out.println("Parakalw eisagete ena minima: ");
				outmsg = app.prepareRequest(scanner.nextLine());
				System.out.println("Stelnetai minima sto server...");
				output.writeUTF(outmsg);
				output.flush();
				inmsg = input.readUTF();
				app.processReply(inmsg);
			} while (!outmsg.equalsIgnoreCase("exit"));

			System.out.println("Eisagete kodiko ptisis: ");
			code = scanner.nextLine();
			System.out.println("Eisagete katastasi: ");
			status = scanner.nextLine();
			System.out.println("Eisagete xrono: ");
			time = scanner.nextLine();

			outmsg = app.write(code, status, time);
			output.writeUTF(outmsg);
			output.flush();
			inmsg = input.readUTF();
			app.processReply(inmsg);

			System.out.println("Eisagete kodiko ptisis: ");
			code = scanner.nextLine();
			outmsg = app.read(code);
			output.writeUTF(outmsg);
			output.flush();
			inmsg = input.readUTF();
			app.processReply(inmsg);

			dataSocket.close();
		} catch (IOException e) {
			System.out.println("I/O Error " + e);
		} finally {
			scanner.close();
		}
	}

}
