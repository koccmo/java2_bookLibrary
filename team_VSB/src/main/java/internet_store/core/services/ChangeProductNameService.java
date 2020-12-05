package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.requests.ChangeProductNameRequest;
import internet_store.core.responses.ChangeProductNameResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.validators.ChangeProductNameValidator;

import java.util.List;

public class ChangeProductNameService {

    public ChangeProductNameService(Database database, ChangeProductNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    private final Database database;
    private final ChangeProductNameValidator validator;



    public ChangeProductNameResponse changeProductName(ChangeProductNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getProductId();
        String newName = request.getProductNameNew();

        if (!errors.isEmpty()) {
            return new ChangeProductNameResponse(errors);
        } else return new ChangeProductNameResponse(database.changeProductName(id, newName));
    }
}
