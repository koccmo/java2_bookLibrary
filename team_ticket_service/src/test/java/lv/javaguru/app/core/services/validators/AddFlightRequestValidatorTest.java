package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddFlightRequest;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddFlightRequestValidatorTest {

	private AddFlightRequestValidator validator;
	private AddFlightRequest request;
	private Flight flight;

	@Before
	public void setUp () {
		validator = new AddFlightRequestValidator();

		Ticket ticket = new Ticket("Riga", "London", LocalDate.now().plusMonths(1), "11A");
		ticket.setOriginCountry("Latvia");
		ticket.setDestinationCountry("United Kingdom");

		flight = new Flight();
		flight.setTicket(ticket);
	}

	@Test
	public void validPerson () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", 0, errors.size());
	}

	@Test
	public void noNameTest () {
		User user = new User("", "Aleksejevs");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User name', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void userNameContainsDigitsTest () {
		User user = new User("Sergejs1000", "Aleksejevs");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User name', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void userNameContainsSymbolsTest () {
		User user = new User("Sergejs%^&%", "Aleksejevs");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User name', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noSurnameTest () {
		User user = new User("Sergejs", "");

		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User surname', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void userSurnameContainsSymbolsTest () {
		User user = new User("Sergejs", "Alekse%^&%jevs");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User surname', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void userSurnameContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs1000");

		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User surname', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void noOriginCountryTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setOriginCountry(null);
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin country', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void OriginCountryContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setOriginCountry("Latvia88");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin country', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void OriginCountryContainsSymbolsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setOriginCountry("Latvia$#@#$@#");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin country', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noOriginCityTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setOriginCity(null);
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin city', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void OriginCityContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setOriginCity("Riga45646");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin city', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void OriginCityContainsSymbolsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setOriginCity("Riga$#@#$@#");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin city', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noDestinationCountryTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setDestinationCountry(null);
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination country', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void noDestinationCityTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setDestinationCity(null);
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination city', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void destinationCityContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setDestinationCity("Riga45646");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination city', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void destinationCityContainsSymbolsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setDestinationCity("Riga$#@#$@#");
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination city', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noDepartureDateTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setDepartureDate(null);
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Departure date', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void departureBeforeNowDateTest () {
		User user = new User("Sergejs", "Aleksejevs");

		flight.getTicket().setDepartureDate(LocalDate.now().minusMonths(1));
		flight.setUser(user);

		request = new AddFlightRequest(flight);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Departure date', message='Date shouldn't be in past!'}]", errors.toString());
	}
}