package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.edit.*;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.EditFlightResponse;
import lv.javaguru.app.core.response.edit.*;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class EditFlightService {
	private final Database flightDatabase;
	private final UserDatabase userDatabase;
	private final EditFlightRequestValidator validator;


	public EditFlightService (UserDatabase userDatabase, Database flightDatabase, EditFlightRequestValidator validator) {
		this.userDatabase = userDatabase;
		this.flightDatabase = flightDatabase;
		this.validator = validator;
	}


	public EditFlightResponse execute (EditFlightRequest request) {
		List<CodeError> errors = validator.validateId(request.getId());

		if (!flightDatabase.containsKey(request.getId())) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}

		User currentUser = userDatabase.getCurrentUser();

		if (currentUser.getPersonType() != PersonType.ADMIN && !flightDatabase.isUsersReservation(request.getId(), currentUser)) {
			errors.add(new CodeError("Id error", "User don't have ticket with entered ID"));
		}

		if (!errors.isEmpty())
			return new EditFlightResponse(errors);

		Flight flight = flightDatabase.getFlightById(request.getId());

		return new EditFlightResponse(flight);
	}


	public EditFlightResponse executeOriginCityUpdate (EditFlightRequest request) {
		String originCity = request.getNewValue();

		List<CodeError> errors = validator.validate(originCity);

		if (!errors.isEmpty())
			return new EditFlightResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlightId())
				.setOriginCity(originCity);

		String message = "Origin city was updated!";

		return new EditFlightResponse(message);
	}

	public EditFlightResponse executeDestination (EditFlightRequest request) {
		String destinationCity = request.getNewValue();

		List<CodeError> errors = validator.validate(destinationCity);

		if (!errors.isEmpty())
			return new EditFlightResponse(errors);

		flightDatabase.getTicketByFlightId(request.getId())
				.setDestinationCity(destinationCity);

		String message = "Destination city was updated!";

		return new EditFlightResponse(message);
	}


	public EditTicketDepartureDateResponse execute (EditTicketDepartureDateRequest request) {
		List<CodeError> errors = new ArrayList<>();

		flightDatabase.getTicketByFlightId(request.getId()).setDepartDate(request.getDepartureDate());

		return new EditTicketDepartureDateResponse(errors);
	}

	public EditTicketReturnDateResponse execute (EditTicketArrivalDateRequest request) {
		List<CodeError> errors = new ArrayList<>();

		flightDatabase.getTicketByFlightId(request.getId()).setReturnDate(request.getReturnDate());

		return new EditTicketReturnDateResponse(errors);
	}

	public EditTicketSeatResponse execute (EditTicketSeatRequest request) {
		List<CodeError> errors = new ArrayList<>();

		flightDatabase.getTicketByFlightId(request.getId()).setSeat(request.getSeat());

		return new EditTicketSeatResponse(errors);
	}

}
