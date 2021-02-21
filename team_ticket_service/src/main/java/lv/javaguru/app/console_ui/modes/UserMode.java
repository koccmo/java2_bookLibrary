package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.console_ui.FlightUpdateAction;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import org.springframework.context.ApplicationContext;


public class UserMode {

	private final ApplicationContext context;


	public UserMode (ApplicationContext context) {
		this.context = context;
	}


	public void printMainMenu (User currentUser) {
		while (true) {

			userMode_printMainMenu(currentUser);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					FlightAddAction addFlightAction = context.getBean(FlightAddAction.class);
					addFlightAction.execute();
				}
				case 2 -> {
					FlightShowAllAction flightShowAllAction = context.getBean(FlightShowAllAction.class);
					flightShowAllAction.execute();
				}
				case 3 -> {
					FlightUpdateAction editFlightAction = context.getBean(FlightUpdateAction.class);
					editFlightAction.execute();
				}
				case 4 -> {
					FlightDeleteAction deleteFlight = context.getBean(FlightDeleteAction.class);
					deleteFlight.execute();
				}
				case 0 -> {
					LogOutAction logOutAction = context.getBean(LogOutAction.class);
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


	private static void userMode_printMainMenu (User currentUser) {
		BaseFunc.printHeader("MENU", currentUser.getUsername());
		System.out.println(
				"[1] Add flight\n" +
						"[2] Show all flights\n" +
						"[3] Update flight\n" +
						"[4] Delete flight\n" +
						"[0] Log out");
	}
}
