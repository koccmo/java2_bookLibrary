package lv.javaguru.app.database.repository;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;

import java.util.List;

public interface FlightRepository {

	Long addFlight (Flight flight);

	boolean deleteFlightById (Long id);

	boolean deleteFlightsByUserId (Long id);

	Flight getFlightById (Long id);

	List<Flight> getAllFlights ();

	boolean isUserFlight (Long id, User user);

	List<Flight> getAllUserFlights (User user);
}
