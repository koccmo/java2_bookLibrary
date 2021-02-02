package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.response.FlightAddResponse;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.SqlDatabase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class FlightAddServiceTest {
	@Mock
	private Database flights;
	@Mock
	private SqlDatabase d;
	@Mock
	private AddFlightRequestValidator validator;
	@InjectMocks
	private FlightAddService service;

	private Flight flight;
	private User user;
	private Ticket ticket;

	@Before
	public void setUp () {
		user = new User("Jimbo", "Johnson");

		ticket = new Ticket("Riga", "Paphos", LocalDate.now().plusMonths(1), "155d");
		ticket.setOriginCountry("Latvia");
		ticket.setDestinationCountry("Cyprus");

		flight = new Flight(user, ticket);
	}

	@Test
	@Ignore
	public void shouldReturnNoErrors () {
		AddFlightRequest request = new AddFlightRequest(flight);

		List<CodeError> errors = new ArrayList<>();
		Mockito.when(validator.validate(request)).thenReturn(errors);

		FlightAddResponse response = service.execute(request);

		assertFalse(response.hasErrors());
	}

	@Test
	public void shouldReturnUserNoNameError () {
		user.setName(null);

		AddFlightRequest request = new AddFlightRequest(flight);

		List<CodeError> errors = new ArrayList<>();
		errors.add(new CodeError("User Name","Shouldn't be null or empty!"));

		Mockito.when(validator.validate(request)).thenReturn(errors);

		FlightAddResponse response = service.execute(request);

		assertTrue(response.hasErrors());
		Mockito.verifyNoInteractions(flights);
	}
}