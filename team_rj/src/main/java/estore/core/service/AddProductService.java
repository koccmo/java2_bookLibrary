package estore.core.service;

import estore.core.domain.ProductCategory;
import estore.core.validation.CoreError;
import estore.core.validation.AddProductValidator;
import estore.database.ProductCategoryRepository;
import estore.database.ProductRepository;
import estore.core.domain.Product;
import estore.core.requests.AddProductRequest;
import estore.core.responses.AddProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductService {

    private ProductRepository productDB;
    private ProductCategoryRepository categoryDB;
    private AddProductValidator validator;

    public AddProductService(ProductRepository productDB,
                             ProductCategoryRepository categoryDB,
                             AddProductValidator validator) {
        this.productDB = productDB;
        this.categoryDB = categoryDB;
        this.validator = validator;
    }

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        ProductCategory category = new ProductCategory(request.getProductCategory());
        List<ProductCategory> categories = categoryDB.getDatabase();
        for (var item : categories) {
            if (item.getCategory().equalsIgnoreCase(request.getProductCategory())) {
                category.setId(item.getId());
            }
        }
        Product product = new Product(request.getProductName(), request.getProductDescription(), category);

        productDB.addNewProduct(product);
        AddProductResponse response = new AddProductResponse(product);
        response.setSuccessfullyAdded(true);

        return response;
    }

}
