package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.AddNewProductValidator;
import estore.database.ProductDB;
import estore.domain.Product;
import estore.core.requests.AddNewProductRequest;
import estore.core.responses.AddNewProductResponse;

import java.util.List;

public class AddNewProductService {

    private ProductDB productDatabase;
    private AddNewProductValidator validator;

    public AddNewProductService(ProductDB productDatabase, AddNewProductValidator validator) {
        this.productDatabase = productDatabase;
        this.validator = validator;
    }

    public AddNewProductResponse execute(AddNewProductRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (errors.size() > 0) {
            return new AddNewProductResponse(errors);
        }

        Product product = new Product(request.getProductName(), request.getProductDescription(), request.getProductCategory());

        productDatabase.addNewProduct(product);
        AddNewProductResponse response = new AddNewProductResponse(product);
        response.setSuccessfullyAdded(true);

        return response;
    }

}
