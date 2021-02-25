package book_library.core.validators.ReaderBook;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TakeBookValidator {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderBookRepository readerBookRepository;

    public List<CoreError> validate(TakeBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePresenceOfReaderId(request).ifPresent(errors::add);
        validatePresenceOfBookId(request).ifPresent(errors::add);
        validatePresenceOfBookOutDate(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateReaderIdPresenceInReaderRepository(request).ifPresent(errors::add);
            validateBookIdPresenceInBookRepository(request).ifPresent(errors::add);
            validateDateAndTimeEnteredIsBeforeNow(request).ifPresent(errors::add);
            if (errors.isEmpty()) {
                validateIsBookInLibrary(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private Optional<CoreError> validatePresenceOfReaderId(TakeBookRequest request) {
        return (request.getReaderId() == null)
                ? Optional.of(new CoreError("readerId", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePresenceOfBookId(TakeBookRequest request) {
        return (request.getBookId() == null)
                ? Optional.of(new CoreError("bookId", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePresenceOfBookOutDate(TakeBookRequest request) {
        return (request.getBookOutDate() == null)
                ? Optional.of(new CoreError("bookOutDate", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReaderIdPresenceInReaderRepository(TakeBookRequest request) {
        return (readerRepository.isSuchIdPresentsInDatabase(request.getReaderId()))
                ? Optional.empty()
                : Optional.of(new CoreError("readerId", "No reader with such id is present in database!"));

    }

    private Optional<CoreError> validateBookIdPresenceInBookRepository(TakeBookRequest request) {
        return (bookRepository.isSuchIdPresentsInDatabase(request.getBookId()))
                ? Optional.empty()
                : Optional.of(new CoreError("bookId", "No book with such id is present in database!"));

    }

    private Optional<CoreError> validateIsBookInLibrary(TakeBookRequest request) {
        return (readerBookRepository.isBookInLibrary(request.getBookId()))
                ? Optional.empty()
                : Optional.of(new CoreError("bookId", "This book is already taken from Library."));

    }

    private Optional<CoreError> validateDateAndTimeEnteredIsBeforeNow(TakeBookRequest request) {
        Date dateNow = Calendar.getInstance().getTime();
        return (dateNow.after(request.getBookOutDate()))
                ? Optional.empty()
                : Optional.of(new CoreError("bookOutDate", "bookOutDate and time cannot be later than the current date and time."));
    }

}
