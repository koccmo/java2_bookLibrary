package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.core.response.FlightAddResponse;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightAddService {

	@Autowired
	private Database flightDatabase;
	@Autowired
	private AddFlightRequestValidator validator;


	public FlightAddResponse execute (AddFlightRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new FlightAddResponse(errors);

		flightDatabase.addFlight(request.getFlight());

		return new FlightAddResponse();
	}
}
