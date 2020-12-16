package team_VK.application.core.services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.BookBookResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;

import java.util.*;
@DIComponent
public class BookBookService {

    @DIDependency private Database database;
    @DIDependency public BookBookServiceValidator validator;

//    public BookBookService(Database database, BookBookServiceValidator validator) {
//        this.database = database;
//        this.validator = validator;
//    }

    public BookBookResponse bookBook(BookBookRequest request) {

        List<CoreError> errors = validator.validate(request, database);

        if (errors.size() == 0) {

            Optional<Book> bookableBook = database.getListBooks().stream()
                    .filter(book -> book.getID() == request.getBookID())
                    .findFirst();

            Date bookingStartDate = request.bookingStartDate;
            if (bookableBook.isPresent()) {
                BookingPeriod bookingPeriod = getBookingPeriod(bookingStartDate, bookableBook.get().getBookingDurationPermitted());
                bookableBook.get().addBooking(bookingPeriod);
            }
        }
        return new BookBookResponse(errors);
    }

    private BookingPeriod getBookingPeriod(Date bookingStartDate, int bookingDuration) {
        Calendar bookingFinishCalendar = Calendar.getInstance();
        bookingFinishCalendar.setTime(bookingStartDate);
        bookingFinishCalendar.add(Calendar.DAY_OF_YEAR, bookingDuration);

        return new BookingPeriod(bookingStartDate, bookingFinishCalendar.getTime());
    }

}
