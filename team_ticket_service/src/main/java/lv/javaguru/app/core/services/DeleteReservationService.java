package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.DeleteReservationRequest;
import lv.javaguru.app.core.request.EditTicketRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.DeleteReservationResponse;
import lv.javaguru.app.core.response.EditTicketResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class DeleteReservationService {

	private final Database database;

	public DeleteReservationService (Database database) {
		this.database = database;
	}

	public DeleteReservationResponse execute (DeleteReservationRequest request) {
		List<CodeError> errors = new ArrayList<>();//validator.validate(request);

		if (!database.isContainTicketWithId(request.getId()))
			errors.add(new CodeError("Id error", "wrong ID"));

		if (!errors.isEmpty())
			return new DeleteReservationResponse(errors);

		database.removeTicketById(request.getId());

		return new DeleteReservationResponse("Ticket '" + request.getId() + "' deleted!");
	}


}
