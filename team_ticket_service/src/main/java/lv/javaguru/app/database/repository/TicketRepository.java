package lv.javaguru.app.database.repository;

import lv.javaguru.app.core.domain.Ticket;

import java.util.Date;

public interface TicketRepository {

	Long addTicket (Ticket ticket);

	Ticket getTicketById (Long id);

	boolean deleteTicketById (Long id);

	boolean updateTicketDateByTicketId (Long id, Date date);

	boolean updateTicketDestinationByTicketId (Long id, String destinationCountry, String destinationCity);

	boolean updateTicketOriginByTicketId (Long id, String originCountry, String originCity);

	boolean updateTicketSeatByTicketId (Long id, String seat);
}
