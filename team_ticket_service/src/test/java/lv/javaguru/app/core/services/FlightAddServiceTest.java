package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.response.AddFlightResponse;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.database.Database;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightAddServiceTest {
	@Mock
	private Database flights;
	//@Mock
	//private JdbcDatabase d;
	@Mock
	private AddFlightRequestValidator validator;
	@InjectMocks
	private FlightAddService service;

	private Flight flight;
	private User user;
	private Ticket ticket;

	@Before
	public void setUp () {
		this.user = new User("Jimbo", "Johnson");

		this.ticket = new Ticket();
		this.ticket.setOriginCountry("Latvia");
		this.ticket.setOriginCity("Riga");
		this.ticket.setDestinationCountry("United Kingdom");
		this.ticket.setDestinationCity("London");
		this.ticket.setDepartureDate(new Date());
		this.ticket.setSeat("1001");
	}

	@Test
	@Ignore
	public void shouldReturnNoErrors () {
		AddFlightRequest request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = new ArrayList<>();
		Mockito.when(validator.validate(request)).thenReturn(errors);

		AddFlightResponse response = service.execute(request);

		assertFalse(response.hasErrors());
	}

	@Test
	public void shouldReturnUserNoNameError () {
		user.setUsername(null);

		AddFlightRequest request = new AddFlightRequest(user, ticket);

		List<CodeError> errors = new ArrayList<>();
		errors.add(new CodeError("User Name", "Shouldn't be null or empty!"));

		Mockito.when(validator.validate(request)).thenReturn(errors);

		AddFlightResponse response = service.execute(request);

		assertTrue(response.hasErrors());
		Mockito.verifyNoInteractions(flights);
	}
}