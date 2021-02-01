package lv.javaguru.app.database.rowmappers;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightRowMapper implements RowMapper<Flight> {

	@Override
	public Flight mapRow (ResultSet rs, int rowNum) throws SQLException {
		User u = new UserRowMapper().mapRow(rs, rowNum);
		Ticket t = new TicketRowMapper().mapRow(rs, rowNum);

		Flight flight = new Flight(u, t);
		flight.setId(rs.getLong("flights.id"));

		return flight;
	}
}
