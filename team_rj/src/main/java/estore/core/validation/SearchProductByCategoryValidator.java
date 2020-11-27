package estore.core.validation;

import estore.core.requests.SearchProductByCategoryRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchProductByCategoryValidator {

    public List<CoreError> validate(SearchProductByCategoryRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(SearchProductByCategoryRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("ERROR! Product Category ", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(SearchProductByCategoryRequest request) {
        return (!validateString(request.getProductCategory()))
                ? Optional.of(new CoreError("ERROR! Product Category", "Must contain only english letters!"))
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
