package lv.javaguru.app.core.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date departureDate;

	@Column(name = "seat")
	private String seat;


	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public Ticket () {
	}

	public Ticket (String originCity, String destinationCity, Date departureDate, String seat) {
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.departureDate = departureDate;
		this.seat = seat;
	}

	public String getOriginCountry () {
		return originCountry;
	}

	public void setOriginCountry (String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestinationCity () {
		return destinationCity;
	}

	public void setDestinationCity (String destinationCity) {
		this.destinationCity = destinationCity;
	}


	@Override
	public String toString () {
		return "ID: " + id + ", " +
				"FROM: " + originCity + ", " + originCountry +
				", TO: " + destinationCity + ", " + destinationCountry +
				", DATE: " + departureDate +
				", SEAT: " + seat;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ticket ticket = (Ticket) o;
		return Objects.equals(id, ticket.id) &&
				Objects.equals(originCountry, ticket.originCountry) &&
				Objects.equals(originCity, ticket.originCity) &&
				Objects.equals(destinationCountry, ticket.destinationCountry) &&
				Objects.equals(destinationCity, ticket.destinationCity) &&
				Objects.equals(departureDate, ticket.departureDate) &&
				Objects.equals(seat, ticket.seat);
	}

	@Override
	public int hashCode () {
		return Objects.hash(id, originCountry, originCity, destinationCountry, destinationCity, departureDate, seat);
	}

	public String getOriginCity () {
		return originCity;
	}

	public void setOriginCity (String originCity) {
		this.originCity = originCity;
	}

	public String getDestinationCountry () {
		return destinationCountry;
	}

	public void setDestinationCountry (String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public Date getDepartureDate () {
		return departureDate;
	}

	public void setDepartureDate (Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getSeat () {
		return seat;
	}

	public void setSeat (String seat) {
		this.seat = seat;
	}
}
