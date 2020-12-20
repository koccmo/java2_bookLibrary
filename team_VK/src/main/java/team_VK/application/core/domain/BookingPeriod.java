package team_VK.application.core.domain;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

public class BookingPeriod {

    Date bookingStartDate;
    Date bookingFinishDate;

    public BookingPeriod(Date bookingStartDate, Date bookingFinishDate) {
        this.bookingStartDate = bookingStartDate;
        this.bookingFinishDate = bookingFinishDate;
    }

    public Date getBookingStartDate() {
        return bookingStartDate;
    }

    public Date getBookingFinishDate() {
        return bookingFinishDate;
    }

    @Override
    public String toString() {
        return "BookingPeriod{" +
                "bookingStartDate=" + bookingStartDate +
                ", bookingFinishDate=" + bookingFinishDate +
                '}';
    }
}
