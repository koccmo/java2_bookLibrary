package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.console_ui.ShowFlightsAction;
import lv.javaguru.app.console_ui.ShowUsersAction;
import lv.javaguru.app.console_ui.EditUserAction;
import lv.javaguru.app.console_ui.EditFlightAction;
import lv.javaguru.app.console_ui.LogOutAction;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.EditFlightService;
import lv.javaguru.app.core.services.LogOutService;
import lv.javaguru.app.core.services.ShowFlightService;
import lv.javaguru.app.core.services.EditUserService;
import lv.javaguru.app.core.services.ShowUsersService;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.core.services.validators.EditUserValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

public class AdminMode {

	//private final ManageTicketAction manageTicketAction;
	private final EditUserAction editUserAction;

	private final EditFlightAction editFlightAction;

	private final ShowFlightsAction showFlightsAction;
	private final ShowUsersAction showUsersAction;

	private final LogOutAction logOutAction;


	public AdminMode (UserDatabase userDatabase, Database database) {
		EditUserValidator editUserValidator = new EditUserValidator();
		EditUserService editUserService = new EditUserService(userDatabase, editUserValidator);
		this.editUserAction = new EditUserAction(editUserService);

		EditFlightRequestValidator editFlightRequestValidator = new EditFlightRequestValidator();
		EditFlightService editFlightService = new EditFlightService(userDatabase, database, editFlightRequestValidator);

		//ManageTicketService manageTicketService = new ManageTicketService(database);
		this.editFlightAction = new EditFlightAction(editFlightService);

		ShowFlightService showFlightService = new ShowFlightService(userDatabase, database);
		// ShowTicketsService showTicketsService = new ShowTicketsService(database);
		this.showFlightsAction = new ShowFlightsAction(showFlightService);

		ShowUsersService showUsersService = new ShowUsersService(userDatabase);
		this.showUsersAction = new ShowUsersAction(showUsersService);

		LogOutService logOutService = new LogOutService(userDatabase);
		this.logOutAction = new LogOutAction(logOutService);
	}


	public void printMainMenu (User currentUser) {
		while (true) {

			printAdminMode_MainMenu(currentUser);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> editUserAction.execute();
				case 2 -> editFlightAction.execute();

				case 3 -> showUsersAction.execute();
				case 4 -> showFlightsAction.execute();

				case 0 -> {
					logOutAction.execute();
					return;
				}
				default -> {
					System.out.println("Wrong input");
					BaseFunc.printLineSeparator();
				}
			}
		}
	}


	private static void printAdminMode_MainMenu (User currentUser) {
		BaseFunc.printHeader("MENU", currentUser);
		System.out.println(
				"[1] Manage user\n" +
						"[2] Manage ticket\n" +

						System.lineSeparator() +

						"[3] Show users\n" +
						"[4] Show tickets\n" +

						"[0] Log out");
	}
}
