package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.Ticket;

import java.util.List;

public class ShowTicketResponse<P> {

	private final List<P> response;


	public ShowTicketResponse (List<P> response) {
		this.response = response;
	}

	public List<P> getResponse () {
		return response;
	}
}
