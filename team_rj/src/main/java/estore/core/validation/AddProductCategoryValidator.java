package estore.core.validation;

import estore.core.requests.AddProductCategoryRequest;
import estore.database.ProductCategoryRepository;
import estore.core.domain.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductCategoryValidator {

    private final ProductCategoryRepository categoryDB;
    private ValidationRules validationRules;

    public AddProductCategoryValidator(ProductCategoryRepository categoryDB, ValidationRules validationRules) {
        this.categoryDB = categoryDB;
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(AddProductCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryDuplication(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(AddProductCategoryRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("Product category", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(AddProductCategoryRequest request) {
        return (!validationRules.validateLineWithWhitespaces(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryDuplication(AddProductCategoryRequest request) {
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
