package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.UpdateProductRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateProductValidator {

    public List<CoreError> validate(UpdateProductRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(UpdateProductRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("newName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDescription(UpdateProductRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isEmpty())
                ? Optional.of(new CoreError("newDescription", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrice(UpdateProductRequest request) {
        return (request.getProductPrice() == null || request.getProductPrice().compareTo(BigDecimal.ZERO) < 0)
                ? Optional.of(new CoreError("newPrice", "Must not be empty or less than 0!"))
                : Optional.empty();
    }

}
