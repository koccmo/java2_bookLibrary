package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.responses.product.CoreError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductValidator {

    public List<CoreError> validate(AddProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductName(request).ifPresent(errors::add);
        validateProductDescription(request).ifPresent(errors::add);
        validateProductPrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductName(AddProductRequest request) {
        return (request.getProductName() == null || request.getProductName().isBlank()
                ? Optional.of(new CoreError("Name", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateProductDescription(AddProductRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isBlank()
                ? Optional.of(new CoreError("Description", "must not be empty"))
                : Optional.empty());
    }

    private Optional<CoreError> validateProductPrice(AddProductRequest request) {
        return (request.getProductPrice() == null || request.getProductPrice().equals(new BigDecimal("00.00"))
                ? Optional.of(new CoreError("Price", "must be over 00.00"))
                : Optional.empty());
    }
}
