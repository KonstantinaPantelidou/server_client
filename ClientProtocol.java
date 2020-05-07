import java.io.IOException;

public class ClientProtocol {

	public String prepareRequest(String input) throws IOException {
		String output = input;

		return output;
	}

	public void processReply(String input) throws IOException {
		System.out.println(input);
	}

	public String write(String code, String status, String time) {
		return code + "-" + status + "-" + time;
	}

	public String read(String code) {
		return code;
	}

}
