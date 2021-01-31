package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.*;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.SqlDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightEditService {

	@Autowired
	private Database database;
	@Autowired
	private SqlDatabase sqlDatabase;
	@Autowired
	private EditFlightRequestValidator validator;


	public FlightEditResponse execute (EditFlightRequest request) {
		List<CodeError> errors = validator.validateId(request.getRequestId());


		if (sqlDatabase.getUserById(request.getUser().getId()) == null) {
			errors.add(new CodeError("Id error ", "wrong ID"));
		}
		if (errors.size() > 0)
			return new FlightEditResponse(errors);

		if (request.getUser().getPersonType() != PersonType.ADMIN &&
				!sqlDatabase.isUserFlight(request.getRequestId(), request.getUser())) {
			errors.add(new CodeError("Id error", "User don't have ticket with entered ID"));
		}

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		Flight flight = sqlDatabase.getFlightById(request.getRequestId());

		return new FlightEditResponse(flight);
	}


	public FlightEditResponse executeUserNameUpdate (EditFlightValueRequest request) {
		String name = request.getValue();

		List<CodeError> errors = validator.validateName(name);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		sqlDatabase.updateUserNameById(request.getFlight().getUser().getId(), name);

		//if (!userToUpdate.getName().equals(request.getValue())) {
		//	errors.add(new CodeError("Surname", "Haven't managed to update user name!"));
		//	return new FlightEditResponse(errors);
		//}
		String message = "Name was updated!";

		return new FlightEditResponse(message);
	}

	public FlightEditResponse executeUserSurnameUpdate (EditFlightValueRequest request) {
		String surname = request.getValue();

		List<CodeError> errors = validator.validateSurname(surname);

		if (!errors.isEmpty())
			return new FlightEditResponse(errors);

		sqlDatabase.updateUserSurnameById(request.getFlight().getUser().getId(), surname);

		//if (!userToUpdate.getSurname().equals(request.getValue())) {
		//	errors.add(new CodeError("Surname", "Haven't managed to update user name!"));
		//	return new FlightEditResponse(errors);
		//}

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

		//Ticket ticket = database.getTicketByFlightId(request.getFlight().getId());
		//ticket.setOriginCity(originCity);
		//ticket.setOriginCountry(originCountry);
		sqlDatabase.updateTicketOriginByTicketId(request.getFlight().getTicket().getId(), originCountry, originCity);
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

		sqlDatabase.updateTicketDestinationByTicketId(request.getFlight().getTicket().getId(), destinationCountry, destinationCity);

	//	Ticket ticket = database.getTicketByFlightId(request.getFlight().getId());
	//	ticket.setDestinationCity(destinationCity);
	//	ticket.setDestinationCountry(destinationCountry);

		String message = "destination was updated!";

		return new FlightEditResponse(message);
	}


	public FlightEditResponse updateDate (EditFlightValueRequest request) {
		List<CodeError> errors = validator.validateDate(request.getNewDate());

		if (errors.size() != 0)
			return new FlightEditResponse(errors);

		sqlDatabase.updateTicketDateByTicketId(request.getFlight().getTicket().getId(), request.getNewDate().toString());
		//Ticket ticket = database.getTicketByFlightId(request.getFlight().getId());
		//ticket.setDepartureDate(request.getNewDate());
		//String message;
		//if (ticket.getDepartureDate() == request.getNewDate())
		//	message = "Departure date was  successfully updated!";
		//else
		//	message = "Failed to update departure date!";

		return new FlightEditResponse("Googd!");
	}


	public FlightEditResponse updateSeat (EditFlightValueRequest request) {
		List<CodeError> errors = validateSeat(request.getValue());

		if (errors.size() != 0)
			return new FlightEditResponse(errors);

		sqlDatabase.updateTicketSeatByTicketId(request.getFlight().getTicket().getId(), request.getValue());
	//	database.getTicketByFlightId(request.getFlight().getId())
	//			.setSeat(request.getValue());

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
