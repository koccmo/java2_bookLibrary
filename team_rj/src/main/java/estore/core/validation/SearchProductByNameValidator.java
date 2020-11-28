package estore.core.validation;

import estore.core.requests.SearchProductByNameRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchProductByNameValidator {

    public List<CoreError> validate(SearchProductByNameRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductNameIfEmpty(request).ifPresent(errors::add);
        validateProductNameUnallowedPattern(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductNameIfEmpty(SearchProductByNameRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductNameUnallowedPattern(SearchProductByNameRequest request) {
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
