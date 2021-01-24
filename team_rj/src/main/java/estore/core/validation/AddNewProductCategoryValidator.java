package estore.core.validation;

import estore.core.requests.AddNewProductCategoryRequest;
import estore.core.requests.AddNewProductRequest;
import estore.database.ProductCategoryDB;
import estore.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddNewProductCategoryValidator {

    private ProductCategoryDB categoryDB;
    private ValidationRules validationRules;

    public AddNewProductCategoryValidator(ProductCategoryDB categoryDB, ValidationRules validationRules) {
        this.categoryDB = categoryDB;
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(AddNewProductCategoryRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryDuplication(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(AddNewProductCategoryRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("Product category", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(AddNewProductCategoryRequest request) {
        return (!validationRules.validateLineWithWhitespaces(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryDuplication(AddNewProductCategoryRequest request) {
        return (!validateCategoryExistence(request.getProductCategory()))
                ? Optional.empty()
                : Optional.of(new CoreError("Product category", "exists!"));
    }

    private boolean validateCategoryExistence(String categoryName) {
        List<ProductCategory> categories = categoryDB.getDatabase();
        for (var category : categories) {
            if (category.getCategory().equalsIgnoreCase(categoryName)) {
                return true;
            }
        }
        return false;
    }
}
