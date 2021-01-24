package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class FlightEditService {
	@DIDependency
	private Database flightDatabase;
	@DIDependency
	private UserDatabase userDatabase;
	@DIDependency
	private EditFlightRequestValidator validator;


	public FlightEditResponse execute (EditFlightRequest request) {
		List<CodeError> errors = validator.validate(request.getId());


		if (!flightDatabase.containsKey(request.getId())) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}
		if (errors.size() > 0)
			return new FlightEditResponse(errors);


		User currentUser = userDatabase.getCurrentUser();

		if (currentUser.getPersonType() != PersonType.ADMIN && !flightDatabase.isUsersReservation(request.getId(), currentUser)) {
			errors.add(new CodeError("Id error", "User don't have ticket with entered ID"));
		}

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Flight flight = flightDatabase.getFlightById(request.getId());

		return new FlightEditResponse(flight);
	}

	public FlightEditResponse executeUserNameUpdate (EditFlightRequest request) {
		String name = request.getNewValue();

		List<CodeError> errors = validator.validate(name);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		flightDatabase.getUserByFlightId(request.getFlightId())
				.setName(name);

		String message = "Name was updated!";

		return new FlightEditResponse(message);
	}

	public FlightEditResponse executeUserSurnameUpdate (EditFlightRequest request) {
		String surname = request.getNewValue();

		List<CodeError> errors = validator.validate(surname);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		flightDatabase.getUserByFlightId(request.getFlightId())
				.setSurname(surname);

		String message = "Name was updated!";

		return new FlightEditResponse(message);
	}

	public FlightEditResponse updateOriginCity (EditFlightValueRequest request) {
		String originCity = request.getValue();

		List<CodeError> errors = validator.validate(originCity);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setFromCity(originCity);

		String message = "Origin city was updated!";

		return new FlightEditResponse(message);
	}

	public FlightEditResponse updateDestinationCity (EditFlightValueRequest request) {
		String destinationCity = request.getValue();

		List<CodeError> errors = validator.validate(destinationCity);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setToCity(destinationCity);

		String message = "Destination city was updated!";

		return new FlightEditResponse(message);
	}


	public FlightEditResponse updateDate (EditFlightValueRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (errors.size() != 0)
			return new FlightEditResponse(errors);

		flightDatabase.getTicketByFlightId(request.getFlight().getId())
				.setDate(request.getNewDate());

		return new FlightEditResponse("Date updated!");
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
