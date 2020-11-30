package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.*;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;

import java.util.List;

public class DeleteByProductNameService {

    private final Database database;
    private final DeleteByProductNameValidator deleteByNameValidator;

    public DeleteByProductNameService(Database database, DeleteByProductNameValidator validator) {
        this.database = database;
        this.deleteByNameValidator = validator;
    }

    public DeleteByProductNameResponse execute(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = deleteByNameValidator.validate(productNameRequest);
        if (!errors.isEmpty()) {
            return new DeleteByProductNameResponse(errors);
        }
        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());
        return new DeleteByProductNameResponse(isRemoved);
    }

}
