package lv.javaguru.app.core.response;


import java.util.ArrayList;
import java.util.List;

public class ShowTicketResponse<T> extends ArrayList<T> {

	private final List<T> response;
	private Class<T> responseClass;


	public ShowTicketResponse (List<T> response) {
		this.response = response;
	}

	public Class<T> getResponseClass () {
		return responseClass;
	}

	public List<T> getResponse () {
		return response;
	}

	public boolean hasErrors () {
		if (responseClass == CodeError.class)
			return response.isEmpty();
		return false;
	}

	public void printResponse () {
		if (!response.isEmpty())
			response.forEach(System.out::println);
	}
}
