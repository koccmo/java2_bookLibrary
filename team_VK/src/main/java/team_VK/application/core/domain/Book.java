package team_VK.application.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {

    public long ID;
    public String bookTitle;
    public String bookAuthor;
    public List<BookingPeriod> bookings;
    public int bookingDurationPermitted;

    public Book(String bookTitle, String bookAuthor, int bookingDurationPermitted) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookingDurationPermitted = bookingDurationPermitted;
        bookings = new ArrayList<>();
    }

    public List<BookingPeriod> getBookings() {
        return bookings;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookingDurationPermitted() {
        return bookingDurationPermitted;
    }

    public void addBooking (BookingPeriod period) {

        bookings.add(period);

    }



    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

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
