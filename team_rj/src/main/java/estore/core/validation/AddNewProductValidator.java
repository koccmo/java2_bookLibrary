package estore.core.validation;

import estore.core.requests.AddNewProductRequest;
import estore.domain.ProductCategoryEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewProductValidator {

    public List<CoreError> validate(AddNewProductRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductNameIfEmpty(request).ifPresent(errors::add);
        validateProductNameUnallowedPattern(request).ifPresent(errors::add);
        validateProductDescriptionIfEmpty(request).ifPresent(errors::add);
        validateProductDescriptionUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductNameIfEmpty(AddNewProductRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescriptionIfEmpty(AddNewProductRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isEmpty())
                ? Optional.of(new CoreError("Product description", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(AddNewProductRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("Product category", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductNameUnallowedPattern(AddNewProductRequest request) {
        return (!validateString(request.getProductName()))
                ? Optional.of(new CoreError("Product name", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescriptionUnallowedPattern(AddNewProductRequest request) {
        return (!validateLine(request.getProductDescription()))
                ? Optional.of(new CoreError("Product description", "Must contain only english letters and digits!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(AddNewProductRequest request) {
        return (!validateString(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryExistence(AddNewProductRequest request) {
        return (!validateCategoryExistence(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Does not exist!"))
                : Optional.empty();
    }

    private boolean validateCategoryExistence(String category) {
        for (ProductCategoryEnum pc : ProductCategoryEnum.values()) {
            if (pc.name().equals(category)) {
                return true;
            }
        }
        return false;
    }

    public Boolean validateString(String userInput) {
        Pattern pattern = Pattern.compile("[A-Za-z]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public Boolean validateLine(String userInput) {
        userInput = userInput.replaceAll("\\s+","");

        Pattern pattern = Pattern.compile("[A-Za-z_0-9]*");
        Matcher m = pattern.matcher(userInput);
        if (m.matches()) {
            return true;
        }
        return false;
    }
}
