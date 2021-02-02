package estore.core.service;

import estore.core.domain.ProductCategory;
import estore.core.requests.UpdateProductByIdRequest;
import estore.core.responses.UpdateProductByIdResponse;
import estore.core.validation.CoreError;
import estore.core.validation.UpdateProductByIdValidator;
import estore.database.ProductCategoryRepository;
import estore.database.ProductRepository;
import estore.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateProductByIdService {

    private ProductRepository productDB;
    private ProductCategoryRepository categoryDB;
    private UpdateProductByIdValidator validator;

    public UpdateProductByIdService(ProductRepository productDB,
                                    ProductCategoryRepository categoryDB,
                                    UpdateProductByIdValidator validator) {
        this.productDB = productDB;
        this.categoryDB = categoryDB;
        this.validator = validator;
    }

    public UpdateProductByIdResponse execute(UpdateProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new UpdateProductByIdResponse(errors);
        }

        ProductCategory category = new ProductCategory(request.getProductCategory());
        List<ProductCategory> categories = categoryDB.getDatabase();
        for (var item : categories) {
            if (item.getCategory().equalsIgnoreCase(request.getProductCategory())) {
                category.setId(item.getId());
            }
        }

        Product productToUpdate = new Product(
                request.getProductName(),
                request.getProductDescription(),
                category,
                Integer.valueOf(request.getProductQuantity()),
                Double.valueOf(request.getProductPrice()));
        productToUpdate.setId(request.getProductId());
        productDB.updateProduct(productToUpdate);
        return new UpdateProductByIdResponse(productToUpdate, 1);
    }

}
