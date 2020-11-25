package internet_store_1.core.services;

import internet_store_1.core.database.Database;
import internet_store_1.core.domain.Product;
import internet_store_1.core.requests.DeleteProductByNameRequest;
import internet_store_1.core.responses.CoreError;
import internet_store_1.core.responses.DeleteProductByNameResponse;

import java.util.List;

public class DeleteProductService {

    private final Database database;

    private final DeleteProductByNameValidator validator;

    public DeleteProductService(Database database, DeleteProductByNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteProductByNameResponse deleteProductByName(DeleteProductByNameRequest nameRequest) {
        List<CoreError> error = validator.validate(nameRequest);
        if (!error.isEmpty()) {
            return new DeleteProductByNameResponse(error);
        }

        boolean isDeleted = database.deleteByProductName(nameRequest.getProductName());

        return new DeleteProductByNameResponse(isDeleted);
    }

    public boolean delete(Product product) { return database.delete(product); }
}
