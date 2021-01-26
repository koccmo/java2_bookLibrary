package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.*;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightEditService {
	@Autowired
	private Database flightDatabase;
	@Autowired
	private UserDatabase userDatabase;
	@Autowired
	private EditFlightRequestValidator validator;


	public FlightEditResponse execute (EditFlightRequest request) {
		List<CodeError> errors = validator.validateId(request.getRequestId());


		if (!flightDatabase.containsKey(request.getRequestId())) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}
		if (errors.size() > 0)
			return new FlightEditResponse(errors);


		User currentUser = userDatabase.getCurrentUser();

		if (currentUser.getPersonType() != PersonType.ADMIN && !flightDatabase.isUsersFlight(request.getRequestId(), currentUser)) {
			errors.add(new CodeError("Id error", "User don't have ticket with entered ID"));
		}

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Flight flight = flightDatabase.getFlightById(request.getRequestId());

		return new FlightEditResponse(flight);
	}


	public FlightEditResponse executeUserNameUpdate (EditFlightRequest request) {
		String name = request.getNewValue();

		List<CodeError> errors = validator.validateName(name);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		flightDatabase.getUserByFlightId(request.getFlight().getId())
				.setName(name);

		String message = "Name was updated!";

		return new FlightEditResponse(message);
	}

	public FlightEditResponse executeUserSurnameUpdate (EditFlightRequest request) {
		String surname = request.getNewValue();

		List<CodeError> errors = validator.validateSurname(surname);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		User userToUpdate = flightDatabase.getUserByFlightId(request.getRequestId());
		userToUpdate.setSurname(surname);

		String message = "Surname was updated!";

		return new FlightEditResponse(message);
	}


	public FlightEditResponse updateOrigin (EditFlightValueRequest request) {
		String originCountry = request.getNewValues()[0];
		String originCity = request.getNewValues()[1];

		List<CodeError> errors = validator.validateCity(originCity, "Origin city");
		errors.addAll(validator.validateCountry(originCountry, "Origin country"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Ticket ticket = flightDatabase.getTicketByFlightId(request.getFlight().getId());
		ticket.setFromCity(originCity);
		ticket.setFromCountry(originCountry);

		String message = "Origin was updated!";

		return new FlightEditResponse(message);
	}

	public FlightEditResponse updateDestination (EditFlightValueRequest request) {
		String destinationCountry = request.getNewValues()[0];
		String destinationCity = request.getNewValues()[1];

		List<CodeError> errors = validator.validateCity(destinationCity, "Destination city");
		errors.addAll(validator.validateCountry(destinationCountry, "Destination country"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Ticket ticket = flightDatabase.getTicketByFlightId(request.getFlight().getId());
		ticket.setToCity(destinationCity);
		ticket.setToCountry(destinationCountry);

		String message = "destination was updated!";

		return new FlightEditResponse(message);
	}


	public FlightEditResponse updateDate (EditFlightValueRequest request) {
		List<CodeError> errors = validator.validateDate(request.getNewDate());

		if (errors.size() != 0)
			return new FlightEditResponse(errors);

		Ticket ticket = flightDatabase.getTicketByFlightId(request.getFlight().getId());
		ticket.setDate(request.getNewDate());
		String message;
		if (ticket.getDate() == request.getNewDate())
			message = "Departure date was  successfully updated!";
		else
			message = "Failed to update departure date!";

		return new FlightEditResponse(message);
	}


	public FlightEditResponse updateSeat (EditFlightValueRequest request) {
		List<CodeError> errors = validateSeat(request.getValue());

		if (errors.size() != 0)
			return new FlightEditResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setSeat(request.getValue());

		return new FlightEditResponse("Seat updated!");
	}

	private List<CodeError> validateSeat (String seat) {
		List<CodeError> errors = new ArrayList<>();

		if (seat == null || seat.isEmpty()) {
			errors.add(new CodeError("Seat", "Null or empty"));
			return errors;
		}
		else if (seat.split(" ").length != 1)
			errors.add(new CodeError("Seat", "two words"));

		return errors;

	}
}
