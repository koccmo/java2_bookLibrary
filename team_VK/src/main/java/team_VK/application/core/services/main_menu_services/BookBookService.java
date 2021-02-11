package team_VK.application.core.services.main_menu_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.BookBookResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.validators.BookBookServiceValidator;
import team_VK.application.database.BookRepository;
import team_VK.application.database.BookingPeriodsRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class BookBookService {

    @Autowired
    private BookRepository database;
    @Autowired
    private BookingPeriodsRepository bookingPeriods;
    @Autowired public BookBookServiceValidator validator;

    public BookBookResponse bookBook(BookBookRequest request) {

        List<CoreError> errors = validator.validate(request, database);

        if (errors.size() == 0) {

            Optional<Book> bookableBook = database.getListBooks().stream()
                    .filter(book -> book.getID() == request.getBookID())
                    .findFirst();

            Date bookingStartDate = request.bookingStartDate;

            if (bookableBook.isPresent()) {
                BookingPeriod bookingPeriod = getBookingPeriod(1,bookableBook.get(), bookingStartDate, bookableBook.get().getBookingDurationPermitted());
                bookingPeriods.addBooking(bookingPeriod);
//               BookingPeriod bookingPeriod = new BookingPeriod(0, bookableBook.get().getID(), bookingStartDate, bookingStartDate+10);
            }
        }
        return new BookBookResponse(errors);
    }

    private BookingPeriod getBookingPeriod(long clientID, Book bookableBook, Date bookingStartDate, int bookingDuration) {
        Calendar bookingFinishCalendar = Calendar.getInstance();
        bookingFinishCalendar.setTime(bookingStartDate);
        bookingFinishCalendar.add(Calendar.DAY_OF_YEAR, bookingDuration);

        return new BookingPeriod(1, bookableBook.getID(), bookingStartDate, bookingFinishCalendar.getTime());
    }

}
