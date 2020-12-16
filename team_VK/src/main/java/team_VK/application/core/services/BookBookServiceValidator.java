package team_VK.application.core.services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;

import java.util.*;
@DIComponent
public class BookBookServiceValidator {

    public List<CoreError> validate(BookBookRequest request, Database database) {

        List<CoreError> errors = new ArrayList<>();

        Date today = new Date();
        if (request.getBookingStartDate().before(today)) {
            errors.add(new CoreError("bookingStartDate", "Booking Start date must be after TODAY"));
        }


        Optional<Book> bookableBook = database.getListBooks().stream()
                .filter(book -> book.getID() == request.getBookID())
                .findFirst();

        if (bookableBook.isPresent()) {
            if (!bookableBook.get().bookTitle.equals(request.bookTitle)) {
                errors.add(new CoreError("bookTitle", "Field bookTitle doesn't consist book ID"));
            }
            if (!bookableBook.get().bookAuthor.equals(request.bookAuthor)) {
                errors.add(new CoreError("bookTitle", "Field bookAuthor doesn't consist book ID"));
            }

            Calendar bookingFinishDate = getCalendarShiftedFromBookingStartDate(request, bookableBook);
                Optional<BookingPeriod> bp = bookableBook.get().getBookings().stream()
                        .filter(period -> period.getBookingFinishDate().after(bookingFinishDate.getTime()) &&
                                period.getBookingStartDate().before(bookingFinishDate.getTime()) ||
                                period.getBookingFinishDate().after(request.getBookingStartDate()) &&
                                        period.getBookingStartDate().before(request.getBookingStartDate()))
                        .findFirst();
            if (bp.isPresent()) {
                errors.add(new CoreError("bookStartDate", "Enquired booking period is already filled"));
            }
        }else{errors.add(new CoreError("bookID", "Incorrect bookID"));}


        return errors;
    }

    private Calendar getCalendarShiftedFromBookingStartDate(BookBookRequest request, Optional<Book> bookableBook) {
        Calendar bookingFinishDate = Calendar.getInstance();
        bookingFinishDate.setTime(request.bookingStartDate);
        bookingFinishDate.add(Calendar.DAY_OF_YEAR, bookableBook.get().bookingDurationPermitted);
        return bookingFinishDate;
    }


}
