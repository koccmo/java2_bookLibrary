package estore.core.validation;

import estore.core.requests.UpdateProductByIdRequest;
import estore.database.ProductCategoryRepository;
import estore.core.domain.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateProductByIdValidator {

    private ProductCategoryRepository categoryDB;
    private ValidationRules validationRules;

    public UpdateProductByIdValidator(ProductCategoryRepository categoryDB, ValidationRules validationRules) {
        this.categoryDB = categoryDB;
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(UpdateProductByIdRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductNameIfEmpty(request).ifPresent(errors::add);
        validateProductNameUnallowedPattern(request).ifPresent(errors::add);
        validateProductDescriptionIfEmpty(request).ifPresent(errors::add);
        validateProductDescriptionUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        validateProductCategoryExistence(request).ifPresent(errors::add);
        validateProductQuantityUnallowedPattern(request).ifPresent(errors::add);
        validateProductPriceUnallowedPattern(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductNameIfEmpty(UpdateProductByIdRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescriptionIfEmpty(UpdateProductByIdRequest request) {
        return (request.getProductDescription() == null || request.getProductDescription().isEmpty())
                ? Optional.of(new CoreError("Product description", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(UpdateProductByIdRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("Product category", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductNameUnallowedPattern(UpdateProductByIdRequest request) {
        return (!validationRules.validateString(request.getProductName()))
                ? Optional.of(new CoreError("Product name", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductDescriptionUnallowedPattern(UpdateProductByIdRequest request) {
        return (!validationRules.validateLineWithDigits(request.getProductDescription()))
                ? Optional.of(new CoreError("Product description", "Must contain only english letters and digits!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(UpdateProductByIdRequest request) {
        return (!validationRules.validateString(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryExistence(UpdateProductByIdRequest request) {
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

    private Optional<CoreError> validateProductQuantityUnallowedPattern(UpdateProductByIdRequest request) {
        if (request.getProductQuantity() == null || request.getProductQuantity().equals("")) {
            return Optional.empty();
        }
        if (request.getProductQuantity().equals("0")) {
            return Optional.empty();
        }
        return (!validationRules.validatePositiveInteger(request.getProductQuantity()))
                ? Optional.of(new CoreError("Product quantity", "Must contain only digits!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductPriceUnallowedPattern(UpdateProductByIdRequest request) {
        if (request.getProductPrice() == null || request.getProductPrice().equals("")) {
            return Optional.empty();
        }
        return (!validationRules.validatePositiveDouble(request.getProductQuantity()))
                ? Optional.of(new CoreError("Product price", "Must contain only digits and a dot!"))
                : Optional.empty();
    }
}
