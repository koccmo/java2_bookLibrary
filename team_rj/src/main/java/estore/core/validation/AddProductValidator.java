package estore.core.validation;

import estore.core.requests.AddProductRequest;
import estore.database.ProductCategoryRepository;
import estore.core.domain.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductValidator {

    private ProductCategoryRepository categoryDB;
    private ValidationRules validationRules;

    public AddProductValidator(ProductCategoryRepository categoryDB, ValidationRules validationRules) {
        this.categoryDB = categoryDB;
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(AddProductRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateProductNameIfEmpty(request).ifPresent(errors::add);
        validateProductNameUnallowedPattern(request).ifPresent(errors::add);
        validateProductDescriptionIfEmpty(request).ifPresent(errors::add);
        validateProductDescriptionUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryExistence(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductNameIfEmpty(AddProductRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescriptionIfEmpty(AddProductRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isEmpty())
                ? Optional.of(new CoreError("Product description", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(AddProductRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("Product category", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductNameUnallowedPattern(AddProductRequest request) {
        return (!validationRules.validateString(request.getProductName()))
                ? Optional.of(new CoreError("Product name", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescriptionUnallowedPattern(AddProductRequest request) {
        return (!validationRules.validateLineWithDigits(request.getProductDescription()))
                ? Optional.of(new CoreError("Product description", "Must contain only english letters and digits!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(AddProductRequest request) {
        return (!validationRules.validateString(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryExistence(AddProductRequest request) {
        return (!validateCategoryExistence(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Does not exist!"))
                : Optional.empty();
    }

    private boolean validateCategoryExistence(String categoryStr) {
        List<ProductCategory> categories = categoryDB.getDatabase();
        for (var category : categories) {
            if (category.getCategory().equalsIgnoreCase(categoryStr)) {
                return true;
            }
        }
        return false;
    }
}
