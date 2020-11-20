package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.*;
import internet_store.application.core.responses.*;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;
import internet_store.application.core.services.validators.DeleteByProductValidator;
import internet_store.application.database.Database;

import java.util.List;

public class DeleteProductService {

    private final Database database;
    private DeleteByProductNameValidator deleteByNameValidator;
    private DeleteByProductValidator deleteByProductValidator;

    public DeleteProductService(Database database, DeleteByProductNameValidator validator) {
        this.database = database;
        this.deleteByNameValidator = validator;
    }

    public DeleteProductService(Database database, DeleteByProductValidator validator) {
        this.database = database;
        this.deleteByProductValidator = validator;
    }

    public DeleteByProductNameResponse deleteByProductName(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = deleteByNameValidator.validate(productNameRequest);
        if (!errors.isEmpty()) {
            return new DeleteByProductNameResponse(errors);
        }

        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());

        return new DeleteByProductNameResponse(isRemoved);
    }

    public DeleteByProductResponse deleteByProduct(DeleteByProductRequest request) {
        List<CoreError> errors = deleteByProductValidator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteByProductResponse(errors);
        }

        Product productToDelete = new Product(request.getProductName(), request.getProductDescription(), request.getProductPrice());
        database.delete(productToDelete);
        return new DeleteByProductResponse(productToDelete);
    }

    public boolean delete(Product product) {
        return database.delete(product);
    }

}
