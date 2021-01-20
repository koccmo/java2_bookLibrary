package lv.javaguru.app.core.domain;

import java.util.Objects;

public class Ticket {
	private String originCountry;
	private String originCity;
	private String destinationCountry;
	private String destinationCity;
	private String departDate;
	private String returnDate;
	private String seat;
	private boolean isCanceled;
	private boolean isFinished;

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

	public Ticket () {

	}

	public Ticket (String originCity, String destinationCountry, String departDate, String returnDate, String seat) {
		this.originCity = originCity;
		this.destinationCountry = destinationCountry;
		this.departDate = departDate;
		this.returnDate = returnDate;
		this.seat = seat;
	}

	@Override
	public String toString () {
		return originCity + ", " +
				destinationCountry +
				", departure date: " + departDate +
				", return date: " + returnDate +
				", seat no: " + seat;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ticket ticket = (Ticket) o;
		return Objects.equals(originCity, ticket.originCity) &&
				Objects.equals(destinationCountry, ticket.destinationCountry) &&
				Objects.equals(departDate, ticket.departDate) &&
				Objects.equals(returnDate, ticket.returnDate) &&
				Objects.equals(seat, ticket.seat);
	}

	@Override
	public int hashCode () {
		return Objects.hash(originCity, destinationCountry, departDate, returnDate, seat);
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

	public String getDepartDate () {
		return departDate;
	}

	public void setDepartDate (String departDate) {
		this.departDate = departDate;
	}

	public String getReturnDate () {
		return returnDate;
	}

	public void setReturnDate (String returnDate) {
		this.returnDate = returnDate;
	}

	public String getSeat () {
		return seat;
	}

	public void setSeat (String seat) {
		this.seat = seat;
	}
}
