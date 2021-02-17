package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.database.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class FlightDeleteService {

	@Autowired
	private FlightRepository flightRepository;


	public FlightDeleteResponse execute (DeleteFlightRequest request) {
		List<CodeError> errors = validate(request);

		if (!errors.isEmpty())
			return new FlightDeleteResponse(errors);


		if (!flightRepository.deleteFlightById(request.getId())) {
			errors.add(new CodeError("Flight", "Haven't managed to delete flight with Id: " + request.getId()));
			return new FlightDeleteResponse(errors);
		}

		return new FlightDeleteResponse("Flight '" + request.getId() + "' was deleted!");
	}

	private List<CodeError> validate (DeleteFlightRequest request) {
		List<CodeError> errors = new ArrayList<>();

		Flight flight = flightRepository.getFlightById(request.getId());
		if (flight == null) {
			errors.add(new CodeError("Id", "wrong ID"));
		//	return errors;
		}

	//User u;
	//if (request.getUser().getPersonType() != PersonType.ADMIN) {
	//	u = flight.getUser();
	//	if (!u.equals(request.getUser()))
	//		errors.add(new CodeError("Id", "User don't have flight with such ID!"));
	//}

		return errors;
	}

}
