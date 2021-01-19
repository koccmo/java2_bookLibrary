package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.EditTicketRequest;
import lv.javaguru.app.core.request.edit.*;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.EditTicketResponse;
import lv.javaguru.app.core.response.edit.*;
import lv.javaguru.app.core.services.validators.EditTicketRequestValidator;
import lv.javaguru.app.database.Database;

import java.util.ArrayList;
import java.util.List;

public class EditTicketService {
	private final Database database;
	private final EditTicketRequestValidator validator;


	public EditTicketService (Database database, EditTicketRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}


	public EditTicketResponse execute (EditTicketRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!database.isContainTicketWithId(request.getId())) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}
		if (database.getCurrentPerson().getPersonType() != PersonType.ADMIN && !database.isUserHaveTicketWithId(request.getId())) {
			errors.add(new CodeError("Id error", "User dont have ticket with entered ID"));
		}
		if (!errors.isEmpty())
			return new EditTicketResponse(errors);

		return new EditTicketResponse(request.getId());
	}


	public EditTicketDepartureResponse execute (EditTicketDepartureRequest request) {
		List<CodeError> errors = new ArrayList<>();
//
		// if (!(isIdExist(request.getId()))) {
		//     errors.add(new CodeError("Id error ", "wrong ID"));
		// }
		// if (!errors.isEmpty())
		//     return new EditTicketResponse(errors);

		//    database.getTicketById(database.getCurrentPerson(), request.getTicketId()).
		// database.addPerson(request.getPerson(), request.getTicket());

		database.getTicketById(request.getId()).setOriginCity(request.getDepartureCity());

		return new EditTicketDepartureResponse(errors);
	}

	public EditTicketDestinationResponse execute (EditTicketDestinationRequest request) {
		List<CodeError> errors = new ArrayList<>();

		database.getTicketById(request.getId()).setDestinationCountry(request.getDestinationCity());

		return new EditTicketDestinationResponse(errors);
	}


	public EditTicketDepartureDateResponse execute (EditTicketDepartureDateRequest request) {
		List<CodeError> errors = new ArrayList<>();

		database.getTicketById(request.getId()).setDepartDate(request.getDepartureDate());

		return new EditTicketDepartureDateResponse(errors);
	}

	public EditTicketReturnDateResponse execute (EditTicketArrivalDateRequest request) {
		List<CodeError> errors = new ArrayList<>();

		database.getTicketById(request.getId()).setReturnDate(request.getReturnDate());

		return new EditTicketReturnDateResponse(errors);
	}

	public EditTicketSeatResponse execute (EditTicketSeatRequest request) {
		List<CodeError> errors = new ArrayList<>();

		database.getTicketById(request.getId()).setSeat(request.getSeat());

		return new EditTicketSeatResponse(errors);
	}

}
