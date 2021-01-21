package lv.javaguru.app;

import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.LoginRequestValidator;
import lv.javaguru.app.core.services.validators.RegisterRequestValidator;
import lv.javaguru.app.database.ReservationDatabase;
import lv.javaguru.app.database.UserDatabase;

public class Main {
	private static final ReservationDatabase RESERVATION_DATABASE = new ReservationDatabase();
	private static final UserDatabase USER_DATABASE = new UserDatabase();

	private static final LoginRequestValidator loginRequestValidator = new LoginRequestValidator();
	private static final LogInService loginService = new LogInService(USER_DATABASE, RESERVATION_DATABASE, loginRequestValidator);
	private static final UIActions logInAction = new LogInAction(loginService);

	private static final RegisterRequestValidator registerRequestValidator = new RegisterRequestValidator();
	private static final RegisterService registerService = new RegisterService(USER_DATABASE, registerRequestValidator);
	private static final UIActions registerAction = new RegisterAction(registerService);

	private static final UIActions exitAction = new ExitAction();


	public static void main (String[] args) {
		User admin = new User("admin", "admin", PersonType.ADMIN);
		User user1 = new User("Sergejs", "Aleksejevs");
		User user2 = new User("Bill", "Johnson");

		Ticket ticket1 = new Ticket("Riga", "Paphos", "29-01-2021", "03-02-2021", "55");
		Ticket ticket2 = new Ticket("London", "Paphos", "29-01-2021", "03-02-2021", "55");
		Reservation reservation1 = new Reservation(user1, ticket1);
		Reservation reservation2 = new Reservation(user2, ticket2);

		USER_DATABASE.addUser(admin);
		USER_DATABASE.addUser(user1);
		USER_DATABASE.addUser(user2);

		RESERVATION_DATABASE.addReservation(reservation1);
		RESERVATION_DATABASE.addReservation(reservation2);

		while (true) {
			printInitMenu();
			int menuNumber = BaseFunc.getMenuNumberFromUser();
			switch (menuNumber) {
				case 1 -> {
					logInAction.execute();
				}
				case 2 -> {
					registerAction.execute();
				}
				case 0 -> exitAction.execute();
				default -> {
					BaseFunc.printLineSeparator();
				}
			}
		}
	}

	private static void printInitMenu () {
		BaseFunc.printHeader("LOGIN");
		BaseFunc.printLineSeparator();
		System.out.println(
				"[1] Login\n" +
						"[2] Register\n" +
						"[0] Exit");
	}


}

