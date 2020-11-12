package lv.javaguru.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Ticket {
    private String departure;
    private String destination;
    private String departDate;
    private String returnDate;
    private String seat;
    private Long id ;

    public Ticket(String departure, String destination, String departDate, String returnDate, String seat) {
        this.departure = departure;
        this.destination = destination;
        this.departDate = departDate;
        this.returnDate = returnDate;
        this.seat = seat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departDate='" + departDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", seat='" + seat + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(departure, ticket.departure) &&
                Objects.equals(destination, ticket.destination) &&
                Objects.equals(departDate, ticket.departDate) &&
                Objects.equals(returnDate, ticket.returnDate) &&
                Objects.equals(seat, ticket.seat) &&
                Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, departDate, returnDate, seat, id);
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
