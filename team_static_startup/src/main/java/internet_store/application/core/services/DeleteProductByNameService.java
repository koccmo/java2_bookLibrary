package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductNameResponse;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;

import java.util.List;

public class DeleteProductByNameService {

    private final Database database;
    private DeleteByProductNameValidator deleteByNameValidator;

    public DeleteProductByNameService(Database database, DeleteByProductNameValidator validator) {
        this.database = database;
        this.deleteByNameValidator = validator;
    }

    public DeleteByProductNameResponse deleteByProductName(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = deleteByNameValidator.validate(productNameRequest);
        if (!errors.isEmpty()) {
            return new DeleteByProductNameResponse(errors);
        }
        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());
        return new DeleteByProductNameResponse(isRemoved);
    }

}
