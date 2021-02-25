package book_library.core.validators.Book;

import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.RemoveBookRequest;
import book_library.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveBookRequestValidator {

    @Autowired
    private BookRepository bookRepository;

    public List<CoreError> validate(RemoveBookRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateIdPresentsInDatabase(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateId(RemoveBookRequest request) {
        return (request.getBookIdToRemove() == null)
                ? Optional.of(new CoreError("id", "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIdPresentsInDatabase(RemoveBookRequest request) {
        return (bookRepository.isSuchIdPresentsInDatabase(request.getBookIdToRemove()))
                ? Optional.empty()
                : Optional.of(new CoreError("id", "No book with such id found!"));
    }
}
