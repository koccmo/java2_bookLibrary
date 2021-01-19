package lv.javaguru.app.core.response.admin;

import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.ShowTicketResponse;

import java.util.ArrayList;
import java.util.List;

public class ShowUserResponse<T> extends ArrayList<T> {

	private final List<T> response;
	private Class<T> responseClass;


	public ShowUserResponse (List<T> response) {
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
