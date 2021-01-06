package book_library.core.validators;

import book_library.core.database.Database;
import book_library.core.requests.RemoveBookRequest;
import book_library.core.responses.CoreError;
import book_library.dependency_injection.DIComponent;
import book_library.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class RemoveBookRequestValidator {

    @DIDependency private Database database;

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
        return (database.getAllBooks().stream()
                .filter(book -> book.getId().equals(request.getBookIdToRemove()))
                .findFirst()).isEmpty()
                ? Optional.of(new CoreError("id", "No book with such id found!"))
                : Optional.empty();
    }
}
