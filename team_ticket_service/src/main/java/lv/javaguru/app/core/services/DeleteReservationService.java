package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.DeleteReservationRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.DeleteReservationResponse;
import lv.javaguru.app.database.Database;

import java.util.ArrayList;
import java.util.List;

public class DeleteReservationService {

	private final Database reservations;

	public DeleteReservationService (Database reservations) {
		this.reservations = reservations;
	}

	public DeleteReservationResponse execute (DeleteReservationRequest request) {
		List<CodeError> errors = new ArrayList<>();//validator.validate(request);

		if (!reservations.containsKey(request.getId()))
			errors.add(new CodeError("Id error", "wrong ID"));

		if (!errors.isEmpty())
			return new DeleteReservationResponse(errors);

		reservations.removeReservationById(request.getId());

		return new DeleteReservationResponse("Reservation '" + request.getId() + "' was deleted!");
	}


}
