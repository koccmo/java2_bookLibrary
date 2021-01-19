package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.User;

public class Action {
	private static User loggedInUser;

	public void setLoggedInUser (User loggedInUser) {
		Action.loggedInUser = loggedInUser;
	}

	public User getLoggedInUser () {
		return loggedInUser;
	}
}
