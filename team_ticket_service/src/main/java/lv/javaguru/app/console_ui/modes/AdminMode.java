package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.core.services.validators.EditUserValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

public class AdminMode {
	private final UserUpdateAction userUpdateAction;
	private final UserDeleteAction userDeleteAction;
	private final UserShowOneAction userShowOneAction;
	private final UserShowAllAction userShowAllAction;

	private final FlightUpdateAction flightUpdateAction;
	private final FlightDeleteAction flightDeleteAction;
	private final FlightShowUsersAction flightShowUsersAction;
	private final FlightShowAllAction flightShowAllAction;

	private final LogOutAction logOutAction;


	public AdminMode (UserDatabase userDatabase, Database database) {
		EditUserValidator editUserValidator = new EditUserValidator();
		UserEditService userEditService = new UserEditService(userDatabase, editUserValidator);
		this.userUpdateAction = new UserUpdateAction(userEditService);

		UserDeleteService userDeleteService = new UserDeleteService(userDatabase);
		this.userDeleteAction = new UserDeleteAction(userDeleteService);


		EditFlightRequestValidator editFlightRequestValidator = new EditFlightRequestValidator();
		FlightEditService flightEditService = new FlightEditService(userDatabase, database, editFlightRequestValidator);
		this.flightUpdateAction = new FlightUpdateAction(flightEditService);

		FlightDeleteService flightDeleteService = new FlightDeleteService(userDatabase, database);
		this.flightDeleteAction = new FlightDeleteAction(flightDeleteService);

		FlightShowAllService flightShowAllService = new FlightShowAllService(userDatabase, database);
		this.flightShowAllAction = new FlightShowAllAction(flightShowAllService);

		FlightShowOneService flightShowOneService = new FlightShowOneService(userDatabase, database);
		this.flightShowUsersAction = new FlightShowUsersAction(flightShowOneService);

		UserShowAllService userShowAllService = new UserShowAllService(userDatabase);
		this.userShowAllAction = new UserShowAllAction(userShowAllService);

		UserShowSingleService showSingle = new UserShowSingleService(userDatabase);
		this.userShowOneAction = new UserShowOneAction(showSingle);

		LogOutService logOutService = new LogOutService(userDatabase);
		this.logOutAction = new LogOutAction(logOutService);
	}


	public void printMainMenu (User currentUser) {
		while (true) {

			printAdminMode_MainMenu(currentUser);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> adminMode_printManageUser();
				case 2 -> adminMode_printManageFlights();
				case 3 -> userShowAllAction.execute();
				case 4 -> flightShowAllAction.execute();

				case 0 -> {
					logOutAction.execute();
					return;
				}
				default -> {
					System.out.println("Wrong input!");
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

						"[3] Show all users\n" +
						"[4] Show all flights\n" +

						"[0] Log out");
	}

	private void adminMode_printManageUser () {
		while (true) {
			BaseFunc.printHeader("MANAGE USERS");
			System.out.println(
					"[1] Edit user\n" +
							"[2] Delete user\n" +
							"[3] Show user\n" +

							"[0] Back");

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> userUpdateAction.execute();
				case 2 -> userDeleteAction.execute();
				case 3 -> userShowOneAction.execute();

				case 0 -> {
					return;
				}
				default -> {
					System.out.println("Wrong input!");
					BaseFunc.printLineSeparator();
				}
			}
		}
	}

	private void adminMode_printManageFlights () {
		while (true) {
			BaseFunc.printHeader("MANAGE FLIGHTS");
			System.out.println(
					"[1] Edit flight\n" +
							"[2] Delete flight\n" +
							"[3] Show flight\n" +

							"[0] Back");

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> flightUpdateAction.execute();
				case 2 -> flightDeleteAction.execute();
				case 3 -> flightShowUsersAction.execute();

				case 0 -> {
					return;
				}
				default -> {
					System.out.println("Wrong input!");
					BaseFunc.printLineSeparator();
				}
			}
		}
	}
}
