package lv.javaguru.app.console_ui.admin_side;

import lv.javaguru.app.console_ui.EditTicketAction;
import lv.javaguru.app.console_ui.LogOutAction;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.EditReservationService;
import lv.javaguru.app.core.services.LogOutService;
import lv.javaguru.app.core.services.ShowReservationsService;
import lv.javaguru.app.core.services.admin_side.ManageUserService;
import lv.javaguru.app.core.services.admin_side.ShowUsersService;
import lv.javaguru.app.core.services.validators.EditTicketRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

public class AdminMode {

	//private final ManageTicketAction manageTicketAction;
	private final ManageUserAction manageUserAction;

	private final EditTicketAction editTicketAction;

	private final ShowReservationsAction showReservationsAction;
	private final ShowUsersAction showUsersAction;

	private final LogOutAction logOutAction;


	public AdminMode (UserDatabase userDatabase, Database database) {
		ManageUserService manageUserService = new ManageUserService(userDatabase);
		this.manageUserAction = new ManageUserAction(manageUserService);

		EditTicketRequestValidator editTicketRequestValidator = new EditTicketRequestValidator();
		EditReservationService editReservationService = new EditReservationService(userDatabase, database, editTicketRequestValidator);

		//ManageTicketService manageTicketService = new ManageTicketService(database);
		this.editTicketAction = new EditTicketAction(editReservationService);

		ShowReservationsService showReservationsService = new ShowReservationsService(userDatabase,database);
		// ShowTicketsService showTicketsService = new ShowTicketsService(database);
		this.showReservationsAction = new ShowReservationsAction(showReservationsService);

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
				case 1 -> manageUserAction.execute();
				case 2 -> editTicketAction.execute();

				case 3 -> showUsersAction.execute();
				case 4 -> showReservationsAction.execute();

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
		BaseFunc.printLineSeparator();
		System.out.println(
				"[1] Manage user\n" +
						"[2] Manage ticket\n" +

						System.lineSeparator() +

						"[3] Show users\n" +
						"[4] Show tickets\n" +

						"[0] Log out");
	}
}
