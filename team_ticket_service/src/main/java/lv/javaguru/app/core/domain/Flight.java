package lv.javaguru.app.core.domain;

public class Flight {

	private Long id;
	private User user;
	private Ticket ticket;


	public Flight () {

	}

	public Flight (User user, Ticket ticket) {
		this.user = user;
		this.ticket = ticket;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}

	public Ticket getTicket () {
		return ticket;
	}

	public void setTicket (Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString () {
		return "id: " + id +
				", " + user +
				", " + ticket;
	}
}
