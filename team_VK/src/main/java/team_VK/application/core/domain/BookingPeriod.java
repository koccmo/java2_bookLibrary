package team_VK.application.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="bookings")

public class BookingPeriod {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long bookingID;

    @Column(name="client_id", nullable = false)
    public long clientID;
    @Column(name="book_id", nullable = false)
    public long bookID;
    @Column(name="bookingStartDate", nullable = false)
    Date bookingStartDate;
    @Column(name="bookingFinishDate", nullable = false)
    Date bookingFinishDate;

    public BookingPeriod() {}

    public BookingPeriod(Date bookingStartDate, Date bookingFinishDate) {
        this.bookingStartDate = bookingStartDate;
        this.bookingFinishDate = bookingFinishDate;
    }

    public long getBookingID() {return bookingID;}
    public void setBookingID(long bookingID) {this.bookingID = bookingID;}

    public long getClientID() {return clientID;}
    public void setClientID(long clientID) { this.clientID = clientID;}

    public long getBookID() {return bookID;}
    public void setBookID(long bookID) {this.bookID = bookID;}

    public Date getBookingStartDate() {
        return bookingStartDate;
    }
    public void setBookingStartDate(Date bookingStartDate) {this.bookingStartDate = bookingStartDate;}

    public Date getBookingFinishDate() {
        return bookingFinishDate;
    }
    public void setBookingFinishDate(Date bookingFinishDate) {this.bookingFinishDate = bookingFinishDate;}

    @Override
    public String toString() {
        return "BookingPeriod{" +
                "bookingStartDate=" + bookingStartDate +
                ", bookingFinishDate=" + bookingFinishDate +
                '}';
    }
}
