package team_VK.application.core.services.additional_functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.database.BookingPeriodsRepository;

import java.util.Calendar;
import java.util.Optional;

@Component
public class GetBookingPeriod {

    @Autowired
    private BookingPeriodsRepository bookingPeriods;

    public Optional<BookingPeriod> getBookingPeriod(BookBookRequest request, Optional<Book> bookableBook) {
        Calendar bookingFinishDate = getCalendarShiftedFromBookingStartDate(request, bookableBook);
        return bookingPeriods.getBookingsList(bookableBook.get()).stream()
                .filter(period -> period.getBookingFinishDate().after(bookingFinishDate.getTime()) &&
                        period.getBookingStartDate().before(bookingFinishDate.getTime()) ||
                        period.getBookingFinishDate().after(request.getBookingStartDate()) &&
                                period.getBookingStartDate().before(request.getBookingStartDate()))
                .findFirst();
    }

    private Calendar getCalendarShiftedFromBookingStartDate(BookBookRequest request, Optional<Book> bookableBook) {
        Calendar bookingFinishDate = Calendar.getInstance();
        bookingFinishDate.setTime(request.bookingStartDate);
        bookingFinishDate.add(Calendar.DAY_OF_YEAR, bookableBook.get().bookingDurationPermitted);
        return bookingFinishDate;
    }


}
