package estore.core.service;

import estore.core.requests.AddNewProductCategoryRequest;
import estore.core.responses.AddNewProductCategoryResponse;
import estore.core.validation.AddNewProductCategoryValidator;
import estore.core.validation.CoreError;
import estore.database.ProductCategoryDB;
import estore.domain.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddNewProductCategoryService {

    private ProductCategoryDB productCategoryDB;
    private AddNewProductCategoryValidator validator;

    public AddNewProductCategoryService(ProductCategoryDB productCategoryDB, AddNewProductCategoryValidator validator) {
        this.productCategoryDB = productCategoryDB;
        this.validator = validator;
    }

    public AddNewProductCategoryResponse execute(AddNewProductCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (errors.size() > 0) {
            return new AddNewProductCategoryResponse(errors);
        }

        ProductCategory category = new ProductCategory(request.getProductCategory());

        productCategoryDB.addNewCategory(category);
        AddNewProductCategoryResponse response = new AddNewProductCategoryResponse(category);
        response.setSuccessfullyAdded(true);

        return response;
    }
}
