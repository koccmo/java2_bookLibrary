package lv.javaguru.app.console_ui.user_side;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.AddTicketRequestValidator;
import lv.javaguru.app.core.services.validators.EditTicketRequestValidator;
import lv.javaguru.app.database.Database;


public class UserMode {
	private static final AddTicketRequestValidator validator = new AddTicketRequestValidator();
	private static final EditTicketRequestValidator editTicketRequestValidator = new EditTicketRequestValidator();

	private final UIActions addReservationAction;//= new AddReservationAction(addTicketService);
	private final UIActions showReservationsAction;//= new ShowReservationsAction(showReservationsService);
	private final UIActions editTicketAction;//= new EditTicketAction(editTicketService);
	private final UIActions deleteReservationAction;//= new DeleteReservationAction(deleteReservationService);


	private final UIActions logOutAction;// = new LogOutAction(logOutService);

	public UserMode (Database database) {
		AddTicketService addTicketService = new AddTicketService(database, validator);
		DeleteReservationService deleteReservationService = new DeleteReservationService(database);
		ShowReservationsService showReservationsService = new ShowReservationsService(database);
		EditTicketService editTicketService = new EditTicketService(database, editTicketRequestValidator);
		LogOutService logOutService = new LogOutService(database);

		this.editTicketAction = new EditTicketAction(editTicketService);
		this.addReservationAction = new AddReservationAction(addTicketService);
		this.deleteReservationAction = new DeleteReservationAction(deleteReservationService);
		this.showReservationsAction = new ShowReservationsAction(showReservationsService);
		this.logOutAction = new LogOutAction(logOutService);
	}




	public void printMainMenu (User currentUser) {
		while (true) {
			printUserMode_MainMenu(currentUser);
			int menuNumber = BaseFunc.getMenuNumberFromUser();
			switch (menuNumber) {
				case 1 -> addReservationAction.execute();
				case 2 -> showReservationsAction.execute();
				case 3 -> editTicketAction.execute();
				case 4 -> deleteReservationAction.execute();
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


	private static void printUserMode_MainMenu (User currentUser) {
		BaseFunc.printHeader("MENU", currentUser);
		BaseFunc.printLineSeparator();
		System.out.println(
				"[1] Add ticket to list\n" +
						"[2] Show all reservations\n" +
						"[3] Edit ticket\n" +
						"[4] Delete ticket\n" +
						"[0] Log out");
	}
}
