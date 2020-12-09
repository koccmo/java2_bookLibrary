package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.*;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;
import internet_store.application.dependency_injection.DIComponent;
import internet_store.application.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class DeleteByProductNameService {

    @DIDependency private Database database;
    @DIDependency private DeleteByProductNameValidator deleteByNameValidator;

    public DeleteByProductNameResponse execute(DeleteByProductNameRequest productNameRequest) {
        List<CoreError> errors = deleteByNameValidator.validate(productNameRequest);
        if (!errors.isEmpty()) {
            return new DeleteByProductNameResponse(errors);
        }
        boolean isRemoved = database.deleteByProductName(productNameRequest.getProductName());
        return new DeleteByProductNameResponse(isRemoved);
    }

}
