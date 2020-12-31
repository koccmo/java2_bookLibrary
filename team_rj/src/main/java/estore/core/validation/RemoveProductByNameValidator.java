package estore.core.validation;

import estore.core.requests.RemoveProductByNameRequest;
import estore.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DIComponent
public class RemoveProductByNameValidator {

    public List<CoreError> validate(RemoveProductByNameRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductNameIfEmpty(request).ifPresent(errors::add);
        validateProductNameUnallowedPattern(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductNameIfEmpty(RemoveProductByNameRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductNameUnallowedPattern(RemoveProductByNameRequest request) {
        return (!validateString(request.getProductName()))
                ? Optional.of(new CoreError("Product Name", "Must contain only english letters!"))
                : Optional.empty();
    }

    public Boolean validateString(String userInput) {
        Pattern pattern = Pattern.compile("[A-Za-z]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }


}
