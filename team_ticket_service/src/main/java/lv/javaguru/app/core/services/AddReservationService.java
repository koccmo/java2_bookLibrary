package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.request.AddTicketRequest;
import lv.javaguru.app.core.services.validators.AddTicketRequestValidator;
import lv.javaguru.app.core.response.AddTicketResponse;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.database.UserDatabase;

import java.util.List;

public class AddReservationService {

	private final Database database;
	private final AddTicketRequestValidator validator;

	public AddReservationService (Database database, AddTicketRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}


	public AddTicketResponse execute (AddTicketRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new AddTicketResponse(errors);

		database.addReservation(request.getReservation());

		return new AddTicketResponse();
	}
}
