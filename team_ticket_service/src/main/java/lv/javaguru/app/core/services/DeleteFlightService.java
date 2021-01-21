package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.DeleteFlightResponse;
import lv.javaguru.app.database.Database;

import java.util.ArrayList;
import java.util.List;

public class DeleteFlightService {

	private final Database reservations;

	public DeleteFlightService (Database reservations) {
		this.reservations = reservations;
	}

	public DeleteFlightResponse execute (DeleteFlightRequest request) {
		List<CodeError> errors = new ArrayList<>();//validator.validate(request);

		if (!reservations.containsKey(request.getId()))
			errors.add(new CodeError("Id error", "wrong ID"));

		if (!errors.isEmpty())
			return new DeleteFlightResponse(errors);

		reservations.removeReservationById(request.getId());

		return new DeleteFlightResponse("Reservation '" + request.getId() + "' was deleted!");
	}


}
