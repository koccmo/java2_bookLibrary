package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.requests.DeleteByProductNameRequest;
import internet_store.lesson_3.core.requests.DeleteByProductRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.responses.DeleteByProductNameResponse;
import internet_store.lesson_3.core.responses.DeleteByProductResponse;
import internet_store.lesson_3.core.services.validators.DeleteByProductNameValidator;
import internet_store.lesson_3.core.services.validators.DeleteByProductValidator;
import internet_store.lesson_3.database.Database;

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
