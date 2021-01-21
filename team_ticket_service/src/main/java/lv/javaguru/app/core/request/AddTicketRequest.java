package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Reservation;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.Ticket;

public class AddTicketRequest {
	//private final User currUser;
	//private final Ticket ticket;
	private final Reservation Reservation;

	public AddTicketRequest (Reservation reservation) {
		Reservation = reservation;
	}

	public Reservation getReservation () {
		return Reservation;
	}
	//public AddTicketRequest(User currUser, Ticket ticket) {
	//    this.currUser = currUser;
	//    this.ticket = ticket;
	//}

	//public User getCurrUser () {
	//    return currUser;
	//}

	//public Ticket getTicket() {

}
