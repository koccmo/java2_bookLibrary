package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.DeleteByProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductResponse;
import internet_store.application.core.services.validators.DeleteByProductValidator;

import java.util.List;

public class DeleteProductByProductService {

    private final Database database;
    private DeleteByProductValidator deleteByProductValidator;

    public DeleteProductByProductService(Database database, DeleteByProductValidator validator) {
        this.database = database;
        this.deleteByProductValidator = validator;
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
