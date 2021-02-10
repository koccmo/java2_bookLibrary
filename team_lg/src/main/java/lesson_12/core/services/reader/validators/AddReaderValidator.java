package lesson_12.core.services.reader.validators;

import lesson_12.core.requests.reader.AddReaderRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddReaderValidator {

    public List<CoreError> validate(AddReaderRequest request) {

        List<CoreError> errors = new ArrayList<>();

        validateReaderFirstName(request).ifPresent(errors::add);
        validateReaderLastName(request).ifPresent(errors::add);
        validateReaderPersonalCode(request).ifPresent(errors::add);
        validateReaderPhoneNumber(request).ifPresent(errors::add);
        validateReaderEmail(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateReaderFirstName(AddReaderRequest request) {
        return (request.getReaderFirstName() == null || request.getReaderFirstName().isBlank())
                ? Optional.of(new CoreError("readerFirstName", "Must not be blank!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReaderLastName(AddReaderRequest request) {
        return (request.getReaderLastName() == null || request.getReaderLastName().isBlank())
                ? Optional.of(new CoreError("readerLastName", "Must not be blank!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReaderPersonalCode(AddReaderRequest request) {
        return (request.getReaderPersonalCode() == null || request.getReaderPersonalCode().isBlank())
                ? Optional.of(new CoreError("readerPersonalCode", "Must not be blank!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReaderPhoneNumber(AddReaderRequest request) {
        return (request.getReaderPhoneNumber() == null || request.getReaderPhoneNumber().isBlank())
                ? Optional.of(new CoreError("readerPhoneNumber", "Must not be blank!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateReaderEmail(AddReaderRequest request) {
        return (request.getReaderEmail() == null || request.getReaderEmail().isBlank())
                ? Optional.of(new CoreError("readerEmail", "Must not be blank!"))
                : Optional.empty();
    }

}
