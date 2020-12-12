package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.DeleteByProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductResponse;
import internet_store.application.core.services.validators.DeleteByProductValidator;
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
