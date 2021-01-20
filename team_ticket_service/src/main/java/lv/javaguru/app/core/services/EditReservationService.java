package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.EditReservationRequest;
import lv.javaguru.app.core.request.edit.*;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.EditTicketResponse;
import lv.javaguru.app.core.response.edit.*;
import lv.javaguru.app.core.services.validators.EditTicketRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class EditReservationService {
	private final Database reservations;
	private final UserDatabase userDatabase;
	private final EditTicketRequestValidator validator;


	public EditReservationService (UserDatabase userDatabase, Database reservations, EditTicketRequestValidator validator) {
		this.userDatabase = userDatabase;
		this.reservations = reservations;
		this.validator = validator;
	}


	public EditTicketResponse execute (EditReservationRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!reservations.containsKey(request.getId())) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}

		User currentUser = userDatabase.getCurrentUser();

		if (currentUser.getPersonType() != PersonType.ADMIN && !reservations.isUsersReservation(request.getId(), currentUser)) {
			errors.add(new CodeError("Id error", "User dont have ticket with entered ID"));
		}

		if (!errors.isEmpty())
			return new EditTicketResponse(errors);

		return new EditTicketResponse(request.getId());
	}


	public EditTicketDepartureResponse execute (EditTicketDepartureRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (!reservations.containsKey(request.getId())) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}

		if (!errors.isEmpty())
			return new EditTicketDepartureResponse(errors);

		reservations.getReservationTicket(request.getId()).setOriginCity(request.getDepartureCity());


		return new EditTicketDepartureResponse(errors);
	}

	public EditTicketDestinationResponse execute (EditTicketDestinationRequest request) {
		List<CodeError> errors = new ArrayList<>();

		reservations.getReservationTicket(request.getId()).setDestinationCountry(request.getDestinationCity());

		return new EditTicketDestinationResponse(errors);
	}


	public EditTicketDepartureDateResponse execute (EditTicketDepartureDateRequest request) {
		List<CodeError> errors = new ArrayList<>();

		reservations.getReservationTicket(request.getId()).setDepartDate(request.getDepartureDate());

		return new EditTicketDepartureDateResponse(errors);
	}

	public EditTicketReturnDateResponse execute (EditTicketArrivalDateRequest request) {
		List<CodeError> errors = new ArrayList<>();

		reservations.getReservationTicket(request.getId()).setReturnDate(request.getReturnDate());

		return new EditTicketReturnDateResponse(errors);
	}

	public EditTicketSeatResponse execute (EditTicketSeatRequest request) {
		List<CodeError> errors = new ArrayList<>();

		reservations.getReservationTicket(request.getId()).setSeat(request.getSeat());

		return new EditTicketSeatResponse(errors);
	}

}
