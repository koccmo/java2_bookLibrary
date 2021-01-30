package team_VK.application.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="books")

public class Book {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long ID;

    @Column(name="title", nullable = false)
    public String bookTitle;
    @Column(name="author", nullable = false)
    public String bookAuthor;

    @Column(name="bookingPeriod", nullable = false)
    public int bookingDurationPermitted;


    public List<BookingPeriod> bookings;

    public Book(String bookTitle, String bookAuthor, int bookingDurationPermitted) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookingDurationPermitted = bookingDurationPermitted;
        bookings = new ArrayList<>();
    }

    public Book() {}

    public List<BookingPeriod> getBookings() {
        return bookings;
    }

    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookingDurationPermitted() {
        return bookingDurationPermitted;
    }
    public void setBookingDurationPermitted(int bookingDurationPermitted) {this.bookingDurationPermitted = bookingDurationPermitted;}


    public void addBooking (BookingPeriod period) {bookings.add(period); }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(bookAuthor, book.bookAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, bookAuthor);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", \"" + bookTitle + '\"' +
                ", " + bookAuthor +
                '}';
    }
}
