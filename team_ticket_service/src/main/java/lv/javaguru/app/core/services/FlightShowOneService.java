package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowOneRequest;
import lv.javaguru.app.core.response.FlightShowOneResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class FlightShowOneService {

	@DIDependency
	private Database flightDatabase;
	@DIDependency
	private UserDatabase userDatabase;


	public FlightShowOneResponse execute (FlightShowOneRequest request) {

		Flight flight = flightDatabase.getFlightById(request.getId());

		return new FlightShowOneResponse(flight);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
