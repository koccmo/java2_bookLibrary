package lv.javaguru.app.core.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fromCountry")
	private String originCountry;

	@Column(name = "fromCity")
	private String originCity;

	@Column(name = "toCountry")
	private String destinationCountry;

	@Column(name = "toCity")
	private String destinationCity;

	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name = "date")
	//private Date departureDate;

	@Column(name = "seat")
	private String seat;

	public Ticket (String originCountry, String originCity, String destinationCountry, String destinationCity, Date departureDate, String seat) {
		this.originCountry = originCountry;
		this.originCity = originCity;
		this.destinationCountry = destinationCountry;
		this.destinationCity = destinationCity;
	//	this.departureDate = departureDate;
		this.seat = seat;
	}

	@Override
	public String toString () {
		return "ID: " + id + ", " +
				"FROM: " + originCity + ", " + originCountry +
				", TO: " + destinationCity + ", " + destinationCountry +
			//	", DATE: " + departureDate +
				", SEAT: " + seat;
	}


}
