package internet_store.lesson_5.core.services;

import internet_store.lesson_5.core.database.Database;
import internet_store.lesson_5.core.requests.ChangeProductNameRequest;
import internet_store.lesson_5.core.responses.*;
import internet_store.lesson_5.core.services.validators.ChangeProductNameValidator;

import java.util.List;

public class ChangeProductNameService {

    private final Database database;
    private final ChangeProductNameValidator validator;


    public ChangeProductNameService(Database database, ChangeProductNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public ChangeProductNameResponse execute(ChangeProductNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getProductId();
        String newName = request.getProductNewName();

        if (!errors.isEmpty()){
            return new ChangeProductNameResponse(errors);
        } else return new ChangeProductNameResponse(database.changeProductName(id, newName));
    }


}
