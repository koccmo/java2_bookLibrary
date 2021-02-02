package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowOneRequest;
import lv.javaguru.app.core.response.FlightShowOneResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.SqlDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightShowOneService {

	@Autowired
	private SqlDatabase sqlDatabase;

	public FlightShowOneResponse execute (FlightShowOneRequest request) {

		Flight flight = sqlDatabase.getFlightById(request.getId());

		return new FlightShowOneResponse(flight);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
