package lv.javaguru.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Ticket {
    private String departure;
    private String destination;
    private GregorianCalendar departDate;
    private GregorianCalendar returnDate;
    private int row;
    private int seat;

    public Ticket(String departure, String destination, GregorianCalendar departDate, GregorianCalendar returnDate, int row, int seat) {
        this.departure = departure;
        this.destination = destination;
        this.departDate = departDate;
        this.returnDate = returnDate;
        this.row = row;
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\n' +
                ", departDate=" + new SimpleDateFormat("yyyy/MM/dd").format(departDate.getTime()) + "\n" +
                ", returnDate=" + new SimpleDateFormat("yyyy/MM/dd").format(returnDate.getTime()) + "\n" +
                ", row=" + row + "\n" +
                ", seat=" + seat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return row == ticket.row &&
                seat == ticket.seat &&
                Objects.equals(departure, ticket.departure) &&
                Objects.equals(destination, ticket.destination) &&
                Objects.equals(departDate, ticket.departDate) &&
                Objects.equals(returnDate, ticket.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, departDate, returnDate, row, seat);
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

    public GregorianCalendar getDepartDate() {
        return departDate;
    }

    public void setDepartDate(GregorianCalendar departDate) {
        this.departDate = departDate;
    }

    public GregorianCalendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(GregorianCalendar returnDate) {
        this.returnDate = returnDate;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
