package book_library.core.validators;

import book_library.Book;
import book_library.core.database.Database;
import book_library.core.requests.AddBookRequest;
import book_library.core.responses.CoreError;
import book_library.dependency_injection.DIComponent;
import book_library.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class AddBookRequestValidator {

    @DIDependency private Database database;

    public List<CoreError> validate(AddBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateAuthor(request).ifPresent(errors::add);
        if(errors.isEmpty()) {
            validatePresenceInDatabase(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePresenceInDatabase(AddBookRequest request) {
        Book addBook = new Book(request.getTitle(), request.getAuthor());
        return database.hasTheSameBookInDatabase(addBook)
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
