package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.Person;

public abstract class Action {
	private static Person loggedInUser;

	public void setLoggedInUser (Person loggedInUser) {
		Action.loggedInUser = loggedInUser;
	}

	public Person getLoggedInUser () {
		return loggedInUser;
	}
}
