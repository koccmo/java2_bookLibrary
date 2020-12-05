package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.requests.DeleteProductByIdRequest;
import internet_store.core.responses.CoreError;
import internet_store.core.responses.DeleteProductByIdResponse;
import internet_store.core.services.validators.DeleteByProductIdValidator;

import java.util.List;

public class DeleteProductByIdService {

    private final Database database;
    private final DeleteByProductIdValidator validator;


    public DeleteProductByIdService(Database database, DeleteByProductIdValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteProductByIdResponse deleteProductById(DeleteProductByIdRequest productIdRequest) {
        List<CoreError> error = validator.validate(productIdRequest);
        if (!error.isEmpty()) {
            return new DeleteProductByIdResponse(error);
        }
        boolean isDeleted = database.deleteByProductId(productIdRequest.getProductId());

        return new DeleteProductByIdResponse(isDeleted);
    }
}
