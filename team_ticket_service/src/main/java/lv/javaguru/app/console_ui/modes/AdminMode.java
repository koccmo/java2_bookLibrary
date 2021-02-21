package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import org.springframework.context.ApplicationContext;

public class AdminMode {

	private final ApplicationContext context;


	public AdminMode (ApplicationContext context) {
		this.context = context;
	}


	public void printMainMenu (User currentUser) {
		while (true) {

			printAdminMode_MainMenu(currentUser);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> adminMode_printManageUser();
				case 2 -> adminMode_printManageFlights();
				case 3 -> {
					UserShowAllAction userShowAllAction = context.getBean(UserShowAllAction.class);
					userShowAllAction.execute();
				}
				case 4 -> {
					FlightShowAllAction flightShowAllAction = context.getBean(FlightShowAllAction.class);
					flightShowAllAction.execute();
				}

				case 0 -> {
					LogOutAction logOutAction = context.getBean(LogOutAction.class);
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
		BaseFunc.printHeader("MENU", currentUser.getUsername());
		System.out.println(
				"[1] Manage users\n" +
						"[2] Manage flights\n\n" +

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
				case 1 -> {
					UserUpdateAction userUpdateAction = context.getBean(UserUpdateAction.class);
					userUpdateAction.execute();
				}
				case 2 -> {
					UserDeleteAction userDeleteAction = context.getBean(UserDeleteAction.class);
					userDeleteAction.execute();
				}
				case 3 -> {
					UserShowOneAction userShowOneAction = context.getBean(UserShowOneAction.class);
					userShowOneAction.execute();
				}

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
				case 1 -> {
					FlightUpdateAction flightUpdateAction = context.getBean(FlightUpdateAction.class);
					flightUpdateAction.execute();
				}
				case 2 -> {
					FlightDeleteAction flightDeleteAction = context.getBean(FlightDeleteAction.class);
					flightDeleteAction.execute();
				}
				case 3 -> {
					FlightShowUsersAction flightShowUsersAction = context.getBean(FlightShowUsersAction.class);
					flightShowUsersAction.execute();
				}

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
