package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.AddNewProductValidator;
import estore.database.ProductDataBase;
import estore.domain.Product;
import estore.core.requests.AddNewProductRequest;
import estore.core.responses.AddNewProductResponse;

import java.util.List;

public class AddNewProductService {

    private ProductDataBase database;
    private AddNewProductValidator validator;

    public AddNewProductService(ProductDataBase database, AddNewProductValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddNewProductResponse execute(AddNewProductRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (errors.size() > 0) {
            return new AddNewProductResponse(errors);
        }

        Product product = new Product(request.getProductName(), request.getProductDescription());

        database.addNewProduct(product);
        AddNewProductResponse response = new AddNewProductResponse(product);
        response.setSuccessfullyAdded(true);

        return response;
    }

}
