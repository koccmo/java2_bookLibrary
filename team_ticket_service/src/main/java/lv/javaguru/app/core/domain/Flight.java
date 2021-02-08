package lv.javaguru.app.core.domain;


import javax.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "users_id", nullable = false)
	private User user;

	@OneToOne
	@JoinColumn(name = "tickets_id", nullable = false)
	private Ticket ticket;


	public Flight () {	}

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
		return "Flight ID: " + id +
				",\n\t" + user +
				",\n\t" + ticket;
	}
}
