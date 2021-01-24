package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class FlightDeleteService {

	@DIDependency
	private Database reservations;
	@DIDependency
	private UserDatabase userDatabase;


	public FlightDeleteResponse execute (DeleteFlightRequest request) {
		List<CodeError> errors = validate(request);

		if (!errors.isEmpty())
			return new FlightDeleteResponse(errors);

		reservations.removeReservationById(request.getId());

		return new FlightDeleteResponse("Reservation '" + request.getId() + "' was deleted!");
	}

	private List<CodeError> validate (DeleteFlightRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (!reservations.containsKey(request.getId())) {
			errors.add(new CodeError("Id", "wrong ID"));

			return errors;
		}

		if (userDatabase.getCurrentUser().getPersonType() != PersonType.ADMIN
				&& userDatabase.getCurrentUser() != reservations.getFlightById(request.getId()).getUser())
			errors.add(new CodeError("Id", "User don't have flight with such ID!"));

		return errors;
	}

}
