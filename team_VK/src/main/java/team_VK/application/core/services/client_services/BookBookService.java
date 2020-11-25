package team_VK.application.core.services.client_services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.BookBookResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.database_Admin.Database;

import javax.xml.crypto.Data;
import java.util.*;

public class BookBookService {

    private final Database database;
    public BookBookServiceValidator validator;

    public BookBookService(Database database, BookBookServiceValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public BookBookResponse bookBook(BookBookRequest request) {

        List<CoreError> errors = validator.validate(request, database);

        if (errors.size() == 0) {

            Date bookingStartDate = request.bookingStartDate;
            int bookingDuration = database.getListBooks().stream()
                    .filter(book -> book.getID() == request.getBookID())
                    .findFirst()
                    .get().bookingDurationPermitted;

            BookingPeriod bookingPeriod = getBookingPeriod(bookingStartDate, bookingDuration);

            Optional<Book> bookableBook = database.getListBooks().stream()
                    .filter(book -> book.getID() == request.getBookID())
                    .findFirst();

            bookableBook.ifPresent(book -> book.addBooking(bookingPeriod));
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
