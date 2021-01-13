package lv.javaguru.app.console_ui;

import lv.javaguru.app.Main;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.services.LogInService;

import java.util.Scanner;

public class LogInAction extends Action implements UIActions {
	private final LogInService loginService;

	public LogInAction (LogInService loginService) {
		this.loginService = loginService;
	}


	@Override
	public void execute () {
		BaseFunc.printHeader("LOGIN:");
		BaseFunc.printLineSeparator();

		Person user = fillPerson();

		LogInRequest request = new LogInRequest(user);
		LogInResponse response = loginService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(System.out::println);
		}
		else if (response.getUser() == null)
			System.out.println("No such user!");
		else {
			System.out.println("Hooray! You have logged in!");

			setLoggedInUser(response.getUser());

			if (response.getUser().getPersonType() == PersonType.ADMIN)
				Main.adminMode_mainMenu(response.getUser());
			else

				Main.userMode_mainMenu(response.getUser());
		}
	}


	private Person fillPerson () {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter name: ");
		String name = scanner.nextLine();

		System.out.println("Enter surname: ");
		String surname = scanner.nextLine();

		return new Person(name, surname);
	}
}
