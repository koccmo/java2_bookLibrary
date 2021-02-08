package team_VK.application.core.services.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;
import team_VK.application.core.requests.BookBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.additional_functions.GetBookingPeriod;
import team_VK.application.database.BookRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Component
public class BookBookServiceValidator {

    @Autowired private GetBookingPeriod gBP;
    public List<CoreError> validate(BookBookRequest request, BookRepository database) {

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

            Optional<BookingPeriod> bp =  gBP.getBookingPeriod(request, bookableBook);
            if (bp.isPresent()) {
                errors.add(new CoreError("bookStartDate", "Enquired booking period is already filled"));
            }
        }else{errors.add(new CoreError("bookID", "Incorrect bookID"));}


        return errors;
    }





}
