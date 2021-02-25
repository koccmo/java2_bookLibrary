package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.*;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketDestination;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketOrigin;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketSeat;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class FlightEditService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private EditFlightRequestValidator validator;


	public FlightEditResponse execute (EditFlightRequest request) {
		List<CodeError> errors = validator.validateId(request.getId());

		if (userRepository.getUserById(request.getUser().getId()) == null) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}
		if (errors.size() > 0)
			return new FlightEditResponse(errors);

		//if (request.getUser().getPersonType() != PersonType.ADMIN &&
		//		!flightRepository.isUserFlight(request.getRequestId(), request.getUser())) {
		//	errors.add(new CodeError("Id error", "User don't have ticket with entered ID"));
		//}

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Flight flight = flightRepository.getFlightById(request.getId());

		if (flight == null) {
			errors.add(new CodeError("Id error", "No flight with entered ID"));
			return new FlightEditResponse(errors);
		}

		return new FlightEditResponse(flight);
	}


	public FlightEditResponse execute (UpdateTicketOrigin request) {
		String originCountry = request.getTicketNewOriginCountry();
		String originCity = request.getTicketNewOriginCity();
		Long id = request.getId();

		List<CodeError> errors = new ArrayList<>();//validator.validateName(name);

		if (!ticketRepository.updateTicketOriginByTicketId(id, originCountry, originCity))
			errors.add(new CodeError("Ticket's origin", "Haven't managed to update ticket's origin!"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		String message = "Ticket's origin was updated!";
		return new FlightEditResponse(message);
	}


	public FlightEditResponse execute (UpdateTicketDestination request) {
		String destinationCountry = request.getTicketNewDestinationCountry();
		String destinationCity = request.getTicketNewDestinationCity();
		Long id = request.getId();

		List<CodeError> errors = new ArrayList<>();//validator.validateName(name);

		if (!ticketRepository.updateTicketDestinationByTicketId(id, destinationCountry, destinationCity))
			errors.add(new CodeError("Ticket's destination", "Haven't managed to update ticket's destination!"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		String message = "Ticket's destination was updated!";
		return new FlightEditResponse(message);
	}

	public FlightEditResponse execute (UpdateTicketSeat request) {
		String seat = request.getTicketNewSeat();
		Long id = request.getId();

		List<CodeError> errors = new ArrayList<>();//validator.validateName(name);

		if (!ticketRepository.updateTicketSeatByTicketId(id, seat))
			errors.add(new CodeError("Ticket's seat", "Haven't managed to update ticket's seat!"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		String message = "Ticket's seat was updated!";
		return new FlightEditResponse(message);
	}


	public FlightEditResponse executeUserNameUpdate (EditFlightValueRequest request) {
		String name = request.getValue();
		Long userId = request.getFlight().getUser().getId();

		List<CodeError> errors = validator.validateName(name);

		if (!userRepository.updateUserNameByUserId(userId, name))
			errors.add(new CodeError("Name", "Haven't managed to update user's name!"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		String message = "User's name was updated!";
		return new FlightEditResponse(message);
	}


	public FlightEditResponse executeUserSurnameUpdate (EditFlightValueRequest request) {
		String surname = request.getValue();

		List<CodeError> errors = validator.validateSurname(surname);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Long userId = request.getFlight().getUser().getId();

		if (!userRepository.updateUserSurnameById(userId, surname)) {
			errors.add(new CodeError("Surname", "Haven't managed to update user's surname!"));
			return new FlightEditResponse(errors);
		}

		String message = "User surname was updated!";

		return new FlightEditResponse(message);
	}


	public FlightEditResponse updateOrigin (EditFlightValueRequest request) {
		String originCountry = request.getNewValues()[0];
		String originCity = request.getNewValues()[1];

		List<CodeError> errors = validator.validateCity(originCity, "Origin city");
		errors.addAll(validator.validateCountry(originCountry, "Origin country"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Long ticketId = request.getFlight().getTicket().getId();

		if (!ticketRepository.updateTicketOriginByTicketId(ticketId, originCountry, originCity)) {
			errors.add(new CodeError("Origin", "Haven't managed to update ticket's origin!"));
			return new FlightEditResponse(errors);
		}
		String message = "Origin was updated!";

		return new FlightEditResponse(message);
	}

	public FlightEditResponse updateDestination (EditFlightValueRequest request) {
		String destinationCountry = request.getNewValues()[0];
		String destinationCity = request.getNewValues()[1];

		List<CodeError> errors = validator.validateCity(destinationCity, "Destination city");
		errors.addAll(validator.validateCountry(destinationCountry, "Destination country"));

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Long ticketId = request.getFlight().getTicket().getId();

		if (!ticketRepository.updateTicketDestinationByTicketId(ticketId, destinationCountry, destinationCity)) {
			errors.add(new CodeError("Destination", "Haven't managed to update ticket's destination!"));
			return new FlightEditResponse(errors);
		}

		String message = "Destination was updated!";

		return new FlightEditResponse(message);
	}


	public FlightEditResponse updateDate (EditFlightValueRequest request) {
		List<CodeError> errors = validator.validateDate(request.getNewDate());

		if (errors.size() != 0)
			return new FlightEditResponse(errors);

		Long ticketId = request.getFlight().getTicket().getId();
		Date ticketDate = request.getNewDate();

		if (!ticketRepository.updateTicketDateByTicketId(ticketId, ticketDate)) {
			errors.add(new CodeError("Date", "Haven't managed to update ticket's date!"));
			return new FlightEditResponse(errors);
		}

		return new FlightEditResponse("Date updated!");
	}


	public FlightEditResponse updateSeat (EditFlightValueRequest request) {
		List<CodeError> errors = validateSeat(request.getValue());

		if (errors.size() != 0)
			return new FlightEditResponse(errors);

		Long ticketId = request.getFlight().getTicket().getId();
		String ticketSeat = request.getValue();

		if (!ticketRepository.updateTicketSeatByTicketId(ticketId, ticketSeat)) {
			errors.add(new CodeError("Seat", "Haven't managed to update ticket's seat!"));
			return new FlightEditResponse(errors);
		}

		return new FlightEditResponse("Seat updated!");
	}

	private List<CodeError> validateSeat (String seat) {
		List<CodeError> errors = new ArrayList<>();

		if (seat == null || seat.isEmpty()) {
			errors.add(new CodeError("Seat", "Null or empty"));
			return errors;
		}
		else if (seat.split(" ").length != 1)
			errors.add(new CodeError("Seat", "two words"));

		return errors;

	}
}
