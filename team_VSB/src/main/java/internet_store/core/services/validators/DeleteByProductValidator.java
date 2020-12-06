package internet_store.core.services.validators;

import internet_store.core.requests.DeleteProductByNameDescriptionPriceRequest;
import internet_store.core.responses.CoreError;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeleteByProductValidator {

    public List<CoreError> validate(DeleteProductByNameDescriptionPriceRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductName(request).ifPresent(errors::add);
        validateProductDescription(request).ifPresent(errors::add);
        validateProductPrice(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateProductName(DeleteProductByNameDescriptionPriceRequest request) {
        return  (request.getProductName() == null || request.getProductName().isEmpty()
            ? Optional.of(new CoreError("Name", "Can't be empty!"))
            : Optional.empty());
    }

    private Optional<CoreError> validateProductDescription(DeleteProductByNameDescriptionPriceRequest request) {
        return  (request.getProductDescription() == null || request.getProductDescription().isEmpty()
                ? Optional.of(new CoreError("Description", "Can't be empty!"))
                : Optional.empty());
    }

    private Optional<CoreError> validateProductPrice(DeleteProductByNameDescriptionPriceRequest request) {
        return  (request.getProductPrice() == null || request.getProductPrice().equals(BigDecimal.valueOf(00.0))
                ? Optional.of(new CoreError("Price", "Can't be empty!"))
                : Optional.empty());
    }

}
