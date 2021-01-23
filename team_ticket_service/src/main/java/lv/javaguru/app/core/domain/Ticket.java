package lv.javaguru.app.core.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket {
	private String fromCountry;
	private String fromCity;
	private String toCountry;
	private String toCity;
	private LocalDate date;
	private String seat;
	private boolean isCanceled;
	private boolean isFinished;
	private boolean isEditing;

	public boolean isEditing () {
		return isEditing;
	}

	public void setEditing (boolean editing) {
		isEditing = editing;
	}

	public boolean isOriginSelected () {
		return fromCountry != null && fromCity != null;
	}


	public Ticket () {
	}

	public Ticket (String fromCity, String toCity, LocalDate date, String seat) {
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.date = date;
		this.seat = seat;
	}

	public String getFromCountry () {
		return fromCountry;
	}

	public void setFromCountry (String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getToCity () {
		return toCity;
	}

	public void setToCity (String toCity) {
		this.toCity = toCity;
	}

	public boolean isCanceled () {
		return isCanceled;
	}

	public void setCanceled (boolean canceled) {
		isCanceled = canceled;
	}

	public boolean isFinished () {
		return isFinished;
	}

	public void setFinished (boolean finished) {
		isFinished = finished;
	}


	@Override
	public String toString () {
		return "FROM: " + fromCity + ", " + fromCountry +
				", TO: " + toCity + ", " + toCountry +
				", DATE: " + date +
				", SEAT: " + seat;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ticket ticket = (Ticket) o;
		return Objects.equals(fromCity, ticket.fromCity) &&
				Objects.equals(toCountry, ticket.toCountry) &&
				Objects.equals(date, ticket.date) &&
				Objects.equals(seat, ticket.seat);
	}

	@Override
	public int hashCode () {
		return Objects.hash(fromCity, toCountry, date, seat);
	}

	public String getFromCity () {
		return fromCity;
	}

	public void setFromCity (String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCountry () {
		return toCountry;
	}

	public void setToCountry (String toCountry) {
		this.toCountry = toCountry;
	}

	public LocalDate getDate () {
		return date;
	}

	public void setDate (LocalDate date) {
		this.date = date;
	}

	public String getSeat () {
		return seat;
	}

	public void setSeat (String seat) {
		this.seat = seat;
	}
}
