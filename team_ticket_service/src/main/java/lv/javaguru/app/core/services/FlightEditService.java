package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.EditFlightResponse;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class FlightEditService {
	private final Database flightDatabase;
	private final UserDatabase userDatabase;
	private final EditFlightRequestValidator validator;


	public FlightEditService (UserDatabase userDatabase, Database flightDatabase, EditFlightRequestValidator validator) {
		this.userDatabase = userDatabase;
		this.flightDatabase = flightDatabase;
		this.validator = validator;
	}


	public EditFlightResponse execute (EditFlightRequest request) {
		List<CodeError> errors = validator.validate(request.getId());

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

	public EditFlightResponse executeUserNameUpdate (EditFlightRequest request) {
		String name = request.getNewValue();

		List<CodeError> errors = validator.validate(name);

		if (!errors.isEmpty())
			return new EditFlightResponse(errors);

		flightDatabase.getUserByFlightId(request.getFlightId())
				.setName(name);

		String message = "Name was updated!";

		return new EditFlightResponse(message);
	}

	public EditFlightResponse executeUserSurnameUpdate (EditFlightRequest request) {
		String surname = request.getNewValue();

		List<CodeError> errors = validator.validate(surname);

		if (!errors.isEmpty())
			return new EditFlightResponse(errors);

		flightDatabase.getUserByFlightId(request.getFlightId())
				.setSurname(surname);

		String message = "Name was updated!";

		return new EditFlightResponse(message);
	}

	public EditFlightResponse updateOriginCity (EditFlightValueRequest request) {
		String originCity = request.getValue();

		List<CodeError> errors = validator.validate(originCity);

		if (!errors.isEmpty())
			return new EditFlightResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setFromCity(originCity);

		String message = "Origin city was updated!";

		return new EditFlightResponse(message);
	}

	public EditFlightResponse updateDestinationCity (EditFlightValueRequest request) {
		String destinationCity = request.getValue();

		List<CodeError> errors = validator.validate(destinationCity);

		if (!errors.isEmpty())
			return new EditFlightResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setToCity(destinationCity);

		String message = "Destination city was updated!";

		return new EditFlightResponse(message);
	}


	public EditFlightResponse updateDate (EditFlightValueRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (errors.size() != 0)
			return new EditFlightResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setDate(request.getNewDate());

		return new EditFlightResponse("Date updated!");
	}


	public EditFlightResponse updateSeat (EditFlightValueRequest request) {
		List<CodeError> errors = validateSeat(request.getValue());

		if (errors.size() != 0)
			return new EditFlightResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setSeat(request.getValue());

		return new EditFlightResponse("Seat updated!");
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
