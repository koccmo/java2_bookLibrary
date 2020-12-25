package internet_store.core.services.validators;

import internet_store.core.requests.AddProductRequest;
import internet_store.core.responses.CoreError;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddProductValidator {

    public List<CoreError> validate(AddProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductName(request).ifPresent(errors::add);
        validateProductDescription(request).ifPresent(errors::add);
        validateProductPrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductName(AddProductRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product name", "Can't be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescription(AddProductRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isEmpty())
                ? Optional.of(new CoreError("Product description", "Can't be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductPrice(AddProductRequest request) {
        return (request.getProductPrice() == null || request.getProductPrice().equals(new BigDecimal("00.0")))
                ? Optional.of(new CoreError("Product price", "Can't be empty!"))
                : Optional.empty();
    }
}
