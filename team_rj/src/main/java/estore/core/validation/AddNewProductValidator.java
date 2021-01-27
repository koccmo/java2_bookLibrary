package estore.core.validation;

import estore.core.requests.AddNewProductRequest;
import estore.database.ProductCategoryRepository;
import estore.core.model.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddNewProductValidator {

    private ProductCategoryRepository categoryDB;
    private ValidationRules validationRules;

    public AddNewProductValidator(ProductCategoryRepository categoryDB, ValidationRules validationRules) {
        this.categoryDB = categoryDB;
        this.validationRules = validationRules;
    }

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
        return (!validationRules.validateString(request.getProductName()))
                ? Optional.of(new CoreError("Product name", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescriptionUnallowedPattern(AddNewProductRequest request) {
        return (!validationRules.validateLineWithDigits(request.getProductDescription()))
                ? Optional.of(new CoreError("Product description", "Must contain only english letters and digits!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(AddNewProductRequest request) {
        return (!validationRules.validatePositiveInteger(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Must contain only digits!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryExistence(AddNewProductRequest request) {
        return (!validateCategoryExistence(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Does not exist!"))
                : Optional.empty();
    }

    private boolean validateCategoryExistence(String categoryId) {
        List<ProductCategory> categories = categoryDB.getDatabase();
        for (var category : categories) {
            if (category.getId().toString().equals(categoryId)) {
                return true;
            }
        }
        return false;
    }
}
