package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserShowSingleResponse {

	private final User user;

	public UserShowSingleResponse (User user) {
		this.user = user;
	}

	public User getUser () {
		return user;
	}
}
