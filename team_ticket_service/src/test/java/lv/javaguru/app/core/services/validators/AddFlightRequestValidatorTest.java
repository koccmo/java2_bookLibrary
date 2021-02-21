package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.AddFlightRequest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddFlightRequestValidatorTest {

	private AddFlightRequestValidator validator;
	private AddFlightRequest request;
	private Ticket ticket;

	@Before
	public void setUp () {
		this.validator = new AddFlightRequestValidator();

		Date currentDatePlusOneHour = new Date(System.currentTimeMillis() + 3600 * 1000);

		this.ticket = new Ticket();
		this.ticket.setOriginCountry("Latvia");
		this.ticket.setOriginCity("Riga");
		this.ticket.setDestinationCountry("United Kingdom");
		this.ticket.setDestinationCity("London");
		this.ticket.setDepartureDate(currentDatePlusOneHour);
		this.ticket.setSeat("1001");
	}

	@Test
	public void validPerson () {
		User user = new User("Sergejs", "Aleksejevs");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", 0, errors.size());
	}

	@Test
	public void noNameTest () {
		User user = new User("", "Aleksejevs");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User name', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void userNameContainsDigitsTest () {
		User user = new User("Sergejs1000", "Aleksejevs");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User name', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void userNameContainsSymbolsTest () {
		User user = new User("Sergejs%^&%", "Aleksejevs");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User name', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noSurnameTest () {
		User user = new User("Sergejs", "");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User surname', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void userSurnameContainsSymbolsTest () {
		User user = new User("Sergejs", "Alekse%^&%jevs");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User surname', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void userSurnameContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs1000");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='User surname', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void noOriginCountryTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setOriginCountry(null);

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin country', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void OriginCountryContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setOriginCountry("Latvia88");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin country', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void OriginCountryContainsSymbolsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setOriginCountry("Latvia$#@#$@#");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin country', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noOriginCityTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setOriginCity(null);

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin city', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void OriginCityContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setOriginCity("Riga45646");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin city', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void OriginCityContainsSymbolsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setOriginCity("Riga$#@#$@#");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Origin city', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noDestinationCountryTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setDestinationCountry(null);

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination country', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void noDestinationCityTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setDestinationCity(null);

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination city', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void destinationCityContainsDigitsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setDestinationCity("Riga45646");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination city', message='The string shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void destinationCityContainsSymbolsTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setDestinationCity("Riga$#@#$@#");

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Destination city', message='The string shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noDepartureDateTest () {
		User user = new User("Sergejs", "Aleksejevs");

		ticket.setDepartureDate(null);

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Departure date', message='The string mustn't be empty!'}]", errors.toString());
	}

	@Test
	public void departureBeforeNowDateTest () {
		User user = new User("Sergejs", "Aleksejevs");

		Date currentDateMinusOneHour = new Date(System.currentTimeMillis() - 3600 * 1000);
		ticket.setDepartureDate(currentDateMinusOneHour);

		request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = validator.validate(request);

		assertEquals("Failed!", "[CodeError{field='Departure date', message='Date shouldn't be in past!'}]", errors.toString());
	}
}