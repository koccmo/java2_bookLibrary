package team_VK.application.database;

import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;

import java.util.List;

public interface BookingPeriodsRepository {

    void addBooking(BookingPeriod bookingPeriod);
    boolean removeBooking(BookingPeriod bookingPeriod);
    List<BookingPeriod> getBookingsList(Book book);

}
