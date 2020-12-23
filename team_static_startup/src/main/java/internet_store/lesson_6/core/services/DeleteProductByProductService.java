package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.domain.Product;
import internet_store.lesson_6.core.requests.DeleteByProductRequest;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.responses.DeleteByProductResponse;
import internet_store.lesson_6.core.services.validators.DeleteByProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteProductByProductService {

    @Autowired
    private Database database;
    @Autowired
    private DeleteByProductValidator deleteByProductValidator;

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
