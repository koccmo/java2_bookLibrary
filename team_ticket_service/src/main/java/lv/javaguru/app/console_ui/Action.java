package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;

import java.util.Scanner;

public class Action {
	private static User loggedInUser;

	public void setLoggedInUser (User loggedInUser) {
		Action.loggedInUser = loggedInUser;
	}

	public User getLoggedInUser () {
		return loggedInUser;
	}

	public User fillPerson () {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter name: ");
		String name = scanner.nextLine();
		name = name.trim();

		System.out.println("Enter surname: ");
		String surname = scanner.nextLine();
		surname = surname.trim();

		return new User(name, surname);
	}

	public User fillRegistrationForm () {
		BaseFunc.printHeader("REGISTRATION");

		return fillPerson();
	}

	public User fillLoginForm () {
		BaseFunc.printHeader("LOGIN");

		return fillPerson();
	}


}
