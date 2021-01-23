package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowOneRequest;
import lv.javaguru.app.core.response.FlightShowOneResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class FlightShowOneService {

	private final Database flightDatabase;
	private final UserDatabase userDatabase;

	public FlightShowOneService (UserDatabase userDatabase, Database flightDatabase) {
		this.userDatabase = userDatabase;
		this.flightDatabase = flightDatabase;
	}


	public FlightShowOneResponse execute (FlightShowOneRequest request) {

		Flight flight = flightDatabase.getFlightById(request.getId());

		return new FlightShowOneResponse(flight);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
