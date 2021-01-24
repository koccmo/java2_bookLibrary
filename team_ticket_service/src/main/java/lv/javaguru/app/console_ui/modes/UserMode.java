package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.ApplicationContext;
import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.console_ui.FlightUpdateAction;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;


public class UserMode {
	private static final ApplicationContext context = ApplicationContext.getInstance();

	private final UIActions addFlightAction;
	private final UIActions flightShowAllAction;
	private final UIActions editFlightAction;
	private final UIActions deleteFlight;
	private final UIActions logOutAction;


	public UserMode () {
		this.addFlightAction = context.getBean(FlightAddAction.class);
		this.flightShowAllAction = context.getBean(FlightShowAllAction.class);
		this.editFlightAction = context.getBean(FlightUpdateAction.class);
		this.deleteFlight = context.getBean(FlightDeleteAction.class);
		this.logOutAction = context.getBean(LogOutAction.class);
	}


	public void printMainMenu (User currentUser) {
		while (true) {

			userMode_printMainMenu(currentUser);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> addFlightAction.execute();
				case 2 -> flightShowAllAction.execute();
				case 3 -> editFlightAction.execute();
				case 4 -> deleteFlight.execute();
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


	private static void userMode_printMainMenu (User currentUser) {
		BaseFunc.printHeader("MENU", currentUser);
		System.out.println(
				"[1] Add flight\n" +
						"[2] Show all flights\n" +
						"[3] Update flight\n" +
						"[4] Delete flight\n" +
						"[0] Log out");
	}
}
