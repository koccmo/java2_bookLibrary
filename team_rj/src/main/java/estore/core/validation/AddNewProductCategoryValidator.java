package estore.core.validation;

import estore.core.requests.AddNewProductCategoryRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewProductCategoryValidator {

    public List<CoreError> validate(AddNewProductCategoryRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        //validateProductCategoryDuplication(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(AddNewProductCategoryRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("ERROR! Product category ", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(AddNewProductCategoryRequest request) {
        return (!validateString(request.getProductCategory()))
                ? Optional.of(new CoreError("ERROR! Product category ", "Must contain only english letters!"))
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
