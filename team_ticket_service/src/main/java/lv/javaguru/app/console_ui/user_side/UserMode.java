package lv.javaguru.app.console_ui.user_side;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.AddTicketRequestValidator;
import lv.javaguru.app.core.services.validators.EditTicketRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;


public class UserMode {
	private static final AddTicketRequestValidator validator = new AddTicketRequestValidator();
	private static final EditTicketRequestValidator editTicketRequestValidator = new EditTicketRequestValidator();

	private final UIActions addReservationAction;//= new AddReservationAction(addTicketService);
	private final UIActions showReservationsAction;//= new ShowReservationsAction(showReservationsService);
	private final UIActions editTicketAction;//= new EditTicketAction(editTicketService);
	private final UIActions deleteReservationAction;//= new DeleteReservationAction(deleteReservationService);


	private final UIActions logOutAction;// = new LogOutAction(logOutService);

	public UserMode (UserDatabase userDatabase, Database reservationDatabase) {

		AddReservationService addReservationService = new AddReservationService(reservationDatabase, validator);
		DeleteReservationService deleteReservationService = new DeleteReservationService(reservationDatabase);
		ShowReservationsService showReservationsService = new ShowReservationsService(userDatabase, reservationDatabase);
		EditReservationService editReservationService = new EditReservationService(userDatabase, reservationDatabase, editTicketRequestValidator);

		LogOutService logOutService = new LogOutService(userDatabase);

		this.editTicketAction = new EditTicketAction(editReservationService);
		this.addReservationAction = new AddReservationAction(addReservationService);
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
