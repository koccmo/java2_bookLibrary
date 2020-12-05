package internet_store.lesson_5.core.services;

import internet_store.lesson_5.core.database.Database;
import internet_store.lesson_5.core.requests.FindByIdRequest;
import internet_store.lesson_5.core.responses.*;
import internet_store.lesson_5.core.services.validators.FindByIdValidator;

import java.util.List;

public class FindByIdService {

    private final Database database;
    private final FindByIdValidator validator;


    public FindByIdService(Database database, FindByIdValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindByIdResponse execute(FindByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByIdResponse(errors);
        }

        Long id = Long.parseLong(request.getProductId());
        return new FindByIdResponse(database.findById(id));
    }
}

