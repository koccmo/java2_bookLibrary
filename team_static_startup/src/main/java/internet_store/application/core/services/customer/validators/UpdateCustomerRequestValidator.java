package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.UpdateCustomerRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateCustomerRequestValidator {

    public List<CoreError> validate(UpdateCustomerRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(request).ifPresent(errors::add);
        validateSecondName(request).ifPresent(errors::add);
        validatePhone(request).ifPresent(errors::add);
        validateEMail(request).ifPresent(errors::add);
        validateAddress(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstName(UpdateCustomerRequest request) {
        return (request.getNewFirstName() == null || request.getNewFirstName().isEmpty())
                ? Optional.of(new CoreError("newFirstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSecondName(UpdateCustomerRequest request) {
        return (request.getNewSecondName() == null || request.getNewSecondName().isEmpty())
                ? Optional.of(new CoreError("newSecondName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePhone(UpdateCustomerRequest request) {
        return (request.getNewPhone() == null || request.getNewPhone().isEmpty())
                ? Optional.of(new CoreError("newPhone", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEMail(UpdateCustomerRequest request) {
        return (request.getNewEMail() == null || request.getNewEMail().isEmpty())
                ? Optional.of(new CoreError("newEMail", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateAddress(UpdateCustomerRequest request) {
        return (request.getNewAddress() == null || request.getNewAddress().isEmpty())
                ? Optional.of(new CoreError("newAddress", "Must not be empty!"))
                : Optional.empty();
    }

}
