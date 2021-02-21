package book_library.core.validators.ReaderBook;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReturnBookValidator {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderBookRepository readerBookRepository;

    public List<CoreError> validate(ReturnBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePresenceOfReaderId(request).ifPresent(errors::add);
        validatePresenceOfBookId(request).ifPresent(errors::add);
        validatePresenceOfBookReturnDate(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateReaderIdPresenceInReaderRepository(request).ifPresent(errors::add);
            validateBookIdPresenceInBookRepository(request).ifPresent(errors::add);
            validateDateAndTimeEnteredIsAfterNow(request).ifPresent(errors::add);
            if (errors.isEmpty()) {
                validateIsBookTakenFromLibrary(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private Optional<CoreError> validatePresenceOfReaderId(ReturnBookRequest request) {
        return (request.getReaderId() == null)
                ? Optional.of(new CoreError("readerId", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePresenceOfBookId(ReturnBookRequest request) {
        return (request.getBookId() == null)
                ? Optional.of(new CoreError("bookId", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePresenceOfBookReturnDate(ReturnBookRequest request) {
        return (request.getBookReturnDate() == null)
                ? Optional.of(new CoreError("bookReturnDate", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReaderIdPresenceInReaderRepository(ReturnBookRequest request) {
        return (readerRepository.isSuchIdPresentsInDatabase(request.getReaderId()))
                ? Optional.empty()
                : Optional.of(new CoreError("readerId", "No reader with such id is present in database!"));

    }

    private Optional<CoreError> validateBookIdPresenceInBookRepository(ReturnBookRequest request) {
        return (bookRepository.isSuchIdPresentsInDatabase(request.getBookId()))
                ? Optional.empty()
                : Optional.of(new CoreError("bookId", "No book with such id is present in database!"));

    }

    private Optional<CoreError> validateIsBookTakenFromLibrary(ReturnBookRequest request) {
        return (!readerBookRepository.isBookInLibrary(request.getBookId()))
                ? Optional.empty()
                : Optional.of(new CoreError("bookId", "This book is already in Library."));

    }

    private Optional<CoreError> validateDateAndTimeEnteredIsAfterNow(ReturnBookRequest request) {
        Date dateNow = Calendar.getInstance().getTime();
        return (dateNow.before(request.getBookReturnDate()))
                ? Optional.empty()
                : Optional.of(new CoreError("bookReturnDate", "bookReturnDate and time cannot be earlier than the current date and time."));
    }
}
