package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.console_ui.ExitAction;
import lv.javaguru.app.console_ui.LogInAction;
import lv.javaguru.app.console_ui.UserAddAction;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.database.Database;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

public class InitMode {

	private final ApplicationContext context;

	public InitMode (ApplicationContext context) {
		this.context = context;
	}


	public void execute () {
		while (true) {
			printInitMenu();

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					LogInAction logInAction = context.getBean(LogInAction.class);
					logInAction.execute();
				}
				case 2 -> {
					UserAddAction userAddAction = context.getBean(UserAddAction.class);
					userAddAction.execute();
				}
				case 0 -> {
					ExitAction exitAction = context.getBean(ExitAction.class);
					exitAction.execute();
					return;
				}
				default -> {
					System.out.println("\nWrong input!\n");
				}
			}
		}
	}


	public void fillDb () {
		User admin = new User("admin", "admin", PersonType.ADMIN);
		User user1 = new User("Sergejs", "Aleksejevs");
		User user2 = new User("Bill", "Johnson");
		LocalDate flightDate = LocalDate.of(2021, 2, 14);

		Ticket ticket1 = new Ticket("Riga", "Paphos", flightDate, "55");
		ticket1.setOriginCountry("Latvia");
		ticket1.setDestinationCountry("Cyprus");

		Ticket ticket2 = new Ticket("London", "Paphos", flightDate, "55");
		ticket2.setOriginCountry("Great Britain");
		ticket2.setDestinationCountry("Cyprus");

		Flight flight1 = new Flight();
		flight1.setUser(user1);
		flight1.setTicket(ticket1);

		Flight flight2 = new Flight(user2, ticket2);


		Database database = context.getBean(Database.class);
		database.addUser(admin);
		database.addUser(user1);
		database.addUser(user2);

		database.addTicket(ticket1);
		database.addTicket(ticket2);
		//Database flightDB = context.getBean(Database.class);

		database.addFlight(flight1);
		database.addFlight(flight2);
	}

	private static void printInitMenu () {
		BaseFunc.printHeader("LOGIN");
		System.out.println(
				"[1] Login\n" +
						"[2] Register\n\n" +
						"[0] Exit");
	}
}
