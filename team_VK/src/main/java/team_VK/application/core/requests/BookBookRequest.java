package team_VK.application.core.requests;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

public class BookBookRequest {

    public long bookID;
    public String bookTitle;
    public String bookAuthor;
    public Date bookingStartDate;

    public BookBookRequest(long bookID, String bookTitle, String bookAuthor, Date bookingStartDate) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookingStartDate = bookingStartDate;
    }

    public long getBookID() {
        return bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Date getBookingStartDate() {
        return bookingStartDate;
    }
}
