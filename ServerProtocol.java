import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ServerProtocol {

	private ConcurrentHashMap<String, ArrayList<String>> flights;

	public ServerProtocol(ConcurrentHashMap<String, ArrayList<String>> flights) {
		this.flights = flights;
	}

	public String echo(String input) {
		System.out.println("Echoing minima ston client: " + input);
		return "Echo from server: " + input;
	}

	public String read(String code) {

		if (flights.containsKey(code))
			return "<Flight: " + code + "> <Status: " + flights.get(code).get(0) + "> <Departure Time: "
					+ flights.get(code).get(1) + ">";
		else
			return "Error: Flight " + code + " doesn't exist";
	}

	public String write(String input) {
		String code, status, time;
		String[] parts;
		ArrayList<String> temp = new ArrayList<String>();

		parts = input.split("-");
		code = parts[0];
		status = parts[1];
		time = parts[2];

		temp.add(status);
		temp.add(time);
		flights.put(code, temp);

		return "Flight :" + code + " successfuly added to table";
	}
}
