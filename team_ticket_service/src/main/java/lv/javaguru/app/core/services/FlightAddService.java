package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.core.response.FlightAddResponse;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class FlightAddService {

	@DIDependency
	private Database flightDatabase;
	@DIDependency
	private AddFlightRequestValidator validator;


	public FlightAddResponse execute (AddFlightRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new FlightAddResponse(errors);

		flightDatabase.addReservation(request.getFlight());

		return new FlightAddResponse();
	}
}
