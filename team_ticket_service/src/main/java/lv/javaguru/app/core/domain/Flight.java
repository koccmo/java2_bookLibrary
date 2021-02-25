package lv.javaguru.app.core.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
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

	@ManyToOne
	@JoinColumn(name = "tickets_id", nullable = false)
	private Ticket ticket;


	public Flight (User user, Ticket ticket) {
		this.user = user;
		this.ticket = ticket;
	}


	@Override
	public String toString () {
		return "Flight ID: " + id +
				",\n\t" + user +
				",\n\t" + ticket;
	}
}
