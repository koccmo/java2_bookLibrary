package lv.javaguru.app.database.rowmappers;

import lv.javaguru.app.core.domain.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TicketRowMapper implements RowMapper<Ticket> {

	@Override
	public Ticket mapRow (ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = new Ticket();

		ticket.setId(rs.getLong("tickets.id"));
		ticket.setOriginCountry(rs.getString("tickets.fromCountry"));
		ticket.setOriginCity(rs.getString("tickets.fromCity"));
		ticket.setDestinationCountry(rs.getString("tickets.toCountry"));
		ticket.setDestinationCity(rs.getString("tickets.toCity"));

		String dateStr = rs.getString("tickets.date").split(" ")[0];
		LocalDate date = LocalDate.parse(dateStr.trim());

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	//	Date d = null;
	//	try {
	//		d = formatter.parse(dateStr.trim());
	//	} catch (ParseException e) {
	//		e.printStackTrace();
	//	}
	//	ticket.setDepartureDate(d);
		ticket.setSeat(rs.getString("tickets.seat"));

		return ticket;
	}
}
