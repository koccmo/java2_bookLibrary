package estore.core.service;

import estore.core.requests.AddProductCategoryRequest;
import estore.core.responses.AddProductCategoryResponse;
import estore.core.validation.AddProductCategoryValidator;
import estore.core.validation.CoreError;
import estore.database.ProductCategoryRepository;
import estore.core.domain.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductCategoryService {

    private ProductCategoryRepository productCategoryDB;
    private AddProductCategoryValidator validator;

    public AddProductCategoryService(ProductCategoryRepository productCategoryDB, AddProductCategoryValidator validator) {
        this.productCategoryDB = productCategoryDB;
        this.validator = validator;
    }

    public AddProductCategoryResponse execute(AddProductCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (errors.size() > 0) {
            return new AddProductCategoryResponse(errors);
        }

        ProductCategory category = new ProductCategory(request.getProductCategory());

        productCategoryDB.addCategory(category);
        AddProductCategoryResponse response = new AddProductCategoryResponse(category);
        response.setSuccessfullyAdded(true);

        return response;
    }
}
