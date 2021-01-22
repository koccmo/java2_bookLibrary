package lv.javaguru.app.core.requestvalidator;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class AddFlightValidatorTest {

	private AddFlightRequestValidator validator;

	@Test
	public void validPerson () {
		validator = new AddFlightRequestValidator();
		User user = new User("Sergejs", "Aleksejevs");
		LocalDate flightDate = LocalDate.of(2021, 2, 14);

		Ticket ticket = new Ticket("Riga", "London", flightDate, "11A");
		//   Reservation reservation = new Reservation(person, ticket);
		//   AddTicketRequest request = new AddTicketRequest(reservation);

		/// List<CodeError> errors = validator.validate(request);
		//    assertEquals("Failed!", 0, errors.size());
	}

	@Test
	public void noNameTest () {
		validator = new AddFlightRequestValidator();
		User user = new User("", "Aleksejevs");
		LocalDate flightDate = LocalDate.of(2021, 2, 14);

		Ticket ticket = new Ticket("Riga", "London", flightDate, "11A");

		//   Reservation reservation = new Reservation(person, ticket);

		//  AddTicketRequest request = new AddTicketRequest(reservation);

		//    List<CodeError> errors = validator.validate(request);
		//     assertEquals("Failed!", "[CodeError{field='personsName', message='Wrong name!'}]", errors.toString());
	}

	@Test
	@Ignore
	public void noSurnameTest () {
		validator = new AddFlightRequestValidator();
		User user = new User("Sergejs", "");
		LocalDate flightDate = LocalDate.of(2021, 2, 14);

		Ticket ticket = new Ticket("Riga", "London", flightDate, "11A");

		//  Reservation reservation = new Reservation(person, ticket);

		//  AddTicketRequest request = new AddTicketRequest(reservation);

		//  List<CodeError> errors = validator.validate(request);
		//  assertEquals("Failed!", "[CodeError{field='personsName', message='Wrong name!'}]", errors.toString());
	}
}