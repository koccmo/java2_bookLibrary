package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Action {
	private static User loggedInUser;

	public void setLoggedInUser (User loggedInUser) {
		Action.loggedInUser = loggedInUser;
	}

	public User getLoggedInUser () {
		return loggedInUser;
	}

	public User enterCredentials () {
		Scanner scanner = new Scanner(System.in);

		String name = ProcessLoginInput(scanner, "Enter name:");
		String surname = ProcessLoginInput(scanner, "Enter surname:");

		return new User(name, surname);
	}

	public User registerUser () {
		Scanner scanner = new Scanner(System.in);

		String name = ProcessRegisterInput(scanner, "Enter name:");

		String surname = ProcessRegisterInput(scanner, "Enter surname:");

		return new User(name, surname);
	}

	private String ProcessLoginInput (Scanner scanner, String s) {
		System.out.println(s);
		String name = scanner.nextLine();
		name = name.trim();

		return name;
	}

	private String ProcessRegisterInput (Scanner scanner, String s) {
		String str = ProcessLoginInput(scanner, s);
		str = str.toLowerCase();
		str = BaseFunc.capitalize(str);

		return str;
	}


	public User fillRegistrationForm () {
		BaseFunc.printHeader("REGISTRATION");

		return registerUser();
	}

	public User fillLoginForm () {
		BaseFunc.printHeader("LOGIN");

		return enterCredentials();
	}


}
