package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.ArrayList;
import java.util.List;

public class UserShowAllResponse<T> extends ArrayList<T> {

	private final List<T> response;
	private Class<T> responseClass;

	public UserShowAllResponse (List<T> response) {
		this.response = response;
	}

	public List<T> getResponse () {
		return response;
	}

	public boolean hasErrors () {
		if (responseClass == CodeError.class)
			return response.isEmpty();
		return false;
	}

	public String getResponseAsString () {
		return response.toString();
	}

	public void printResponse () {
		if (!response.isEmpty())
			for (T s : response) {
				System.out.println("\t" + s);
			}
		//response.forEach(System.out::println);
	}
}
