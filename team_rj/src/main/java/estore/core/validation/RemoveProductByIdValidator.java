package estore.core.validation;

import estore.core.requests.RemoveProductByIdRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveProductByIdValidator {

    public List<CoreError> validate(RemoveProductByIdRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductIdIfEmpty(request).ifPresent(errors::add);
        validateProductIdUnallowedPattern(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductIdIfEmpty(RemoveProductByIdRequest request) {
        return (request.getProductId() == null || request.getProductId().isEmpty())
                ? Optional.of(new CoreError("Product ID", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductIdUnallowedPattern(RemoveProductByIdRequest request) {
        return (!validatePositiveLong(request.getProductId()))
                ? Optional.of(new CoreError("Product ID", "Must contain only digits"))
                : Optional.empty();
    }

    public boolean validatePositiveLong(String userStringInput) {
        long choice;
        try {
            choice = Long.valueOf(userStringInput);
        } catch (Exception e) {
            return false;
        }
        if (choice > 0) {
            return true;
        }
        return false;
    }
}
