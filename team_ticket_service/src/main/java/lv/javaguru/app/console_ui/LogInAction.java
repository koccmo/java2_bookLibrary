package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.admin_side.AdminMode;
import lv.javaguru.app.console_ui.user_side.UserMode;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
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

		User user = fillPerson();

		LogInRequest request = new LogInRequest(user);
		LogInResponse response = loginService.execute(request);


		AdminMode adminMode;

		UserMode userMode;


		if (response.hasErrors()) {
			response.getErrorList().forEach(System.out::println);
		}
		else setLoggedInUser(response.getCurrUser());


		if (response.isAdminMode()) {
			adminMode = response.getAdminMode();
			adminMode.printMainMenu(response.getCurrUser());
		}
		else if (response.isUserMode()) {
			userMode = response.getUserMode();
			userMode.printMainMenu(response.getCurrUser());
		}


	}


	private User fillPerson () {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter name: ");
		String name = scanner.nextLine();

		System.out.println("Enter surname: ");
		String surname = scanner.nextLine();

		return new User(name, surname);
	}
}
