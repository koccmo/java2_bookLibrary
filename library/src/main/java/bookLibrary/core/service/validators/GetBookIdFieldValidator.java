package bookLibrary.core.service.validators;


import bookLibrary.core.request.GetBookIdRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class GetBookIdFieldValidator {

    public List<CoreError> validate (GetBookIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateBookAuthorField(request).ifPresent(errors::add);
        validateBookTitleField(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateBookAuthorField(GetBookIdRequest request) {
        return (request.getAuthor() == null || request.getAuthor().isEmpty())
                ? Optional.of(new CoreError("Author", "Must be fill up!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateBookTitleField(GetBookIdRequest request) {
        return (request.getTitle() == null || request.getTitle().isEmpty())
                ? Optional.of(new CoreError("Title", "Must contain only Numbers!"))
                : Optional.empty();
    }
}