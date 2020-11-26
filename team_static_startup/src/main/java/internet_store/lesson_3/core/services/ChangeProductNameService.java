package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.requests.ChangeProductNameRequest;
import internet_store.lesson_3.core.responses.ChangeProductNameResponse;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.services.validators.ChangeProductNameValidator;
import internet_store.lesson_3.database.Database;

import java.util.List;

public class ChangeProductNameService {

    private final Database database;
    private final ChangeProductNameValidator validator;


    public ChangeProductNameService(Database database, ChangeProductNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public ChangeProductNameResponse changeProductName(ChangeProductNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getProductId();
        String newName = request.getProductNewName();

        if (!errors.isEmpty()){
            return new ChangeProductNameResponse(errors);
        } else return new ChangeProductNameResponse(database.changeProductName(id, newName));
    }


}
