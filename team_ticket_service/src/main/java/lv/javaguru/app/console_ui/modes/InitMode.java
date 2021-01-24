package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.ApplicationContext;
import lv.javaguru.app.console_ui.ExitAction;
import lv.javaguru.app.console_ui.LogInAction;
import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.console_ui.UserAddAction;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.time.LocalDate;

public class InitMode {
	private static final ApplicationContext context = ApplicationContext.getInstance();

	private final UIActions logInAction;
	private final UIActions userAddAction;
	private final UIActions exitAction;


	public InitMode () {
		this.logInAction = context.getBean(LogInAction.class);
		this.userAddAction = context.getBean(UserAddAction.class);
		this.exitAction = context.getBean(ExitAction.class);
	}


	public void fillDb () {
		User admin = new User("admin", "admin", PersonType.ADMIN);
		User user1 = new User("Sergejs", "Aleksejevs");
		User user2 = new User("Bill", "Johnson");
		LocalDate flightDate = LocalDate.of(2021, 2, 14);

		Ticket ticket1 = new Ticket("Riga", "Paphos", flightDate, "55");
		ticket1.setFromCountry("Latvia");
		ticket1.setToCountry("Cyprus");

		Ticket ticket2 = new Ticket("London", "Paphos", flightDate, "55");
		ticket2.setFromCountry("Great Britain");
		ticket2.setToCountry("Cyprus");

		Flight flight1 = new Flight(user1, ticket1);
		Flight flight2 = new Flight(user2, ticket2);


		UserDatabase database = context.getBean(UserDatabase.class);
		database.addUser(admin);
		database.addUser(user1);
		database.addUser(user2);

		Database flightDB = context.getBean(Database.class);

		flightDB.addReservation(flight1);
		flightDB.addReservation(flight2);
	}

	public void execute () {
		while (true) {
			printInitMenu();

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					logInAction.execute();
				}
				case 2 -> {
					userAddAction.execute();
				}
				case 0 -> {
					exitAction.execute();
					return;
				}
				default -> {
					System.out.println("\nWrong input!\n");
				}
			}
		}
	}

	private static void printInitMenu () {
		BaseFunc.printHeader("LOGIN");
		System.out.println(
				"[1] Login\n" +
						"[2] Register\n\n" +
						"[0] Exit");
	}
}
