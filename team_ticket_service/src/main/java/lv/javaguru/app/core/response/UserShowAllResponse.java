package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.ArrayList;
import java.util.List;

public class UserShowAllResponse<T> extends ArrayList<T> {

	private List<T> response;
	private Class<T> responseClass;

	public UserShowAllResponse (List<T> response) {
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
