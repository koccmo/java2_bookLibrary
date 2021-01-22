package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.core.response.AddFlightResponse;
import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class FlightAddService {

	private final Database flightDatabase;
	private final AddFlightRequestValidator validator;

	public FlightAddService (Database flightDatabase, AddFlightRequestValidator validator) {
		this.flightDatabase = flightDatabase;
		this.validator = validator;
	}


	public AddFlightResponse execute (AddFlightRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new AddFlightResponse(errors);

		flightDatabase.addReservation(request.getFlight());

		return new AddFlightResponse();
	}
}
