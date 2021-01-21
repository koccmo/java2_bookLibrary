package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.console_ui.EditFlightAction;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;


public class UserMode {
	private static final AddFlightRequestValidator validator = new AddFlightRequestValidator();
	private static final EditFlightRequestValidator EDIT_FLIGHT_REQUEST_VALIDATOR = new EditFlightRequestValidator();

	private final UIActions addFlightAction;
	private final UIActions showFlightAction;
	private final UIActions editFlightAction;
	private final UIActions deleteFlight;
	private final UIActions logOutAction;


	public UserMode (UserDatabase userDatabase, Database flightDatabase) {

		AddFlightService addFlightService = new AddFlightService(flightDatabase, validator);
		DeleteFlightService deleteFlightService = new DeleteFlightService(flightDatabase);
		ShowFlightService showFlightService = new ShowFlightService(userDatabase, flightDatabase);
		EditFlightService editFlightService = new EditFlightService(userDatabase, flightDatabase, EDIT_FLIGHT_REQUEST_VALIDATOR);

		LogOutService logOutService = new LogOutService(userDatabase);

		this.addFlightAction = new AddFlightAction(addFlightService);
		this.showFlightAction = new ShowFlightsAction(showFlightService);
		this.editFlightAction = new EditFlightAction(editFlightService);
		this.deleteFlight = new DeleteFlightAction(deleteFlightService);
		this.logOutAction = new LogOutAction(logOutService);
	}




	public void printMainMenu (User currentUser) {
		while (true) {

			userMode_printMainMenu(currentUser);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> addFlightAction.execute();
				case 2 -> showFlightAction.execute();
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
				"[1] Add flight to list\n" +
						"[2] Show all flights\n" +
						"[3] Update flight\n" +
						"[4] Delete flight\n" +
						"[0] Log out");
	}
}
