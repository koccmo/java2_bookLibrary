package internet_store.lesson_5.core.services;

import internet_store.lesson_5.core.database.Database;
import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.DeleteByProductRequest;
import internet_store.lesson_5.core.responses.*;
import internet_store.lesson_5.core.services.validators.DeleteByProductValidator;

import java.util.List;

public class DeleteProductByProductService {

    private final Database database;
    private DeleteByProductValidator deleteByProductValidator;

    public DeleteProductByProductService(Database database, DeleteByProductValidator validator) {
        this.database = database;
        this.deleteByProductValidator = validator;
    }

    public DeleteByProductResponse execute(DeleteByProductRequest request) {
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
