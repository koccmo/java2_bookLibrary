package lv.javaguru.app;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.console_ui.admin_side.AdminMode;
import lv.javaguru.app.console_ui.user_side.UserMode;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.EditTicketRequestValidator;
import lv.javaguru.app.core.services.validators.LoginRequestValidator;
import lv.javaguru.app.core.services.validators.RegisterRequestValidator;
import lv.javaguru.app.database.InMemoryDatabase;
import lv.javaguru.app.core.services.validators.AddTicketRequestValidator;

public class Main {
	private static final InMemoryDatabase database = new InMemoryDatabase();

	private static final LoginRequestValidator loginRequestValidator = new LoginRequestValidator();
	private static final LogInService loginService = new LogInService(database, loginRequestValidator);
	private static final UIActions logInAction = new LogInAction(loginService);

	private static final RegisterRequestValidator registerRequestValidator = new RegisterRequestValidator();
	private static final RegisterService registerService = new RegisterService(database, registerRequestValidator);
	private static final UIActions registerAction = new RegisterAction(registerService);

	private static final UIActions exitAction = new ExitAction();




	public static void main (String[] args) {
		User admin = new User("ADMIN", "ADMIN");
		admin.setPersonType(PersonType.ADMIN);

		User user = new User("s", "a");
		user.setPersonType(PersonType.CLIENT);

		database.addUser(admin);
		database.addUser(user);

		while (true) {
			printInitMenu();
			int menuNumber = BaseFunc.getMenuNumberFromUser();
			switch (menuNumber) {
				case 1 -> {
					logInAction.execute();
				}
				case 2 -> {
					registerAction.execute();
				}
				case 0 -> exitAction.execute();
				default -> {
					BaseFunc.printLineSeparator();
				}
			}
		}
	}

	private static void printInitMenu () {
		BaseFunc.printHeader("LOGIN");
		BaseFunc.printLineSeparator();
		System.out.println(
				"[1] Login\n" +
						"[2] Register\n" +
						"[0] Exit");
	}


}

