package electronic_library.core.services.book.validators;

import electronic_library.core.requests.book.AddBookRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddBookValidator {

    public List<CoreError> validate(AddBookRequest request) {

        List<CoreError> errors = new ArrayList<>();

        validateBookTitle(request).ifPresent(errors::add);
        validateBookAuthor(request).ifPresent(errors::add);
        validateBookPrice(request).ifPresent(errors::add);
        validateYearOfBookIssue(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateBookTitle(AddBookRequest request) {
        return (request.getBookTitle() == null || request.getBookTitle().isBlank())
                ? Optional.of(new CoreError("Book Title", "Must not be blank!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateBookAuthor(AddBookRequest request) {
        return (request.getBookAuthor() == null || request.getBookAuthor().isBlank())
                ? Optional.of(new CoreError("Book Author", "Must not be blank!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateBookPrice(AddBookRequest request) {
        return (request.getBookPrice() == null || (new BigDecimal("00.00").compareTo(request.getBookPrice()) == 0))
                ? Optional.of(new CoreError("Book Price", "Must be over 00.00!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateYearOfBookIssue(AddBookRequest request) {
        return (request == null || request.getYearOfBookIssue() <= 0)
                ? Optional.of(new CoreError("Year Of Book Issue", "Must be over 0!"))
                : Optional.empty();
    }

}
