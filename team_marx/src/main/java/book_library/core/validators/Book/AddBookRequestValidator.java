package book_library.core.validators.Book;

import book_library.core.domain.Book;
import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.AddBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddBookRequestValidator {

    @Autowired
    private BookRepository bookRepository;

    public List<CoreError> validate(AddBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateAuthor(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validatePresenceInDatabase(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePresenceInDatabase(AddBookRequest request) {
        Book addBook = new Book(request.getTitle(), request.getAuthor());
        return bookRepository.hasTheSameBookInDatabase(addBook)
                ? Optional.of(new CoreError("Title and author", "Such book already exists!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTitle(AddBookRequest request) {
        return (request.getTitle() == null || request.getTitle().isEmpty())
                ? Optional.of(new CoreError("title", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateAuthor(AddBookRequest request) {
        return (request.getAuthor() == null || request.getAuthor().isEmpty())
                ? Optional.of(new CoreError("author", "Must not be empty"))
                : Optional.empty();
    }
}
