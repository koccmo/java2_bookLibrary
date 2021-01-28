package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightDeleteService {

	@Autowired
	private Database database;


	public FlightDeleteResponse execute (DeleteFlightRequest request) {
		List<CodeError> errors = validate(request);

		if (!errors.isEmpty())
			return new FlightDeleteResponse(errors);

		database.removeFlightById(request.getId());

		if (database.getFlightById(request.getId()) != null) {
			errors.add(new CodeError("Flight", "Haven't managed to delete flight with Id: " + request.getId()));
			return new FlightDeleteResponse(errors);
		}

		return new FlightDeleteResponse("Flight '" + request.getId() + "' was deleted!");
	}

	private List<CodeError> validate (DeleteFlightRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (!database.flightTableContainsId(request.getId())) {
			errors.add(new CodeError("Id", "wrong ID"));

			return errors;
		}

		if (database.getCurrentUser().getPersonType() != PersonType.ADMIN
				&& database.getCurrentUser() != database.getFlightById(request.getId()).getUser())
			errors.add(new CodeError("Id", "User don't have flight with such ID!"));

		return errors;
	}

}
