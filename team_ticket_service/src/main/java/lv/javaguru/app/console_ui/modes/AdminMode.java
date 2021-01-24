package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.ApplicationContext;
import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;


public class AdminMode {
	private static final ApplicationContext context = ApplicationContext.getInstance();

	private final UserUpdateAction userUpdateAction;
	private final UserDeleteAction userDeleteAction;
	private final UserShowOneAction userShowOneAction;
	private final UserShowAllAction userShowAllAction;

	private final FlightUpdateAction flightUpdateAction;
	private final FlightDeleteAction flightDeleteAction;
	private final FlightShowUsersAction flightShowUsersAction;
	private final FlightShowAllAction flightShowAllAction;

	private final LogOutAction logOutAction;


	public AdminMode () {
		this.userUpdateAction = context.getBean(UserUpdateAction.class);
		this.userDeleteAction = context.getBean(UserDeleteAction.class);
		this.flightUpdateAction = context.getBean(FlightUpdateAction.class);
		this.flightDeleteAction = context.getBean(FlightDeleteAction.class);
		this.flightShowAllAction = context.getBean(FlightShowAllAction.class);
		this.flightShowUsersAction = context.getBean(FlightShowUsersAction.class);
		this.userShowAllAction = context.getBean(UserShowAllAction.class);
		this.userShowOneAction = context.getBean(UserShowOneAction.class);
		this.logOutAction = context.getBean(LogOutAction.class);
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
						"[2] Manage ticket\n\n" +

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
