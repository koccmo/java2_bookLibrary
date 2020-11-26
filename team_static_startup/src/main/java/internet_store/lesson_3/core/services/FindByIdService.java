package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.requests.FindByIdRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.responses.FindByIdResponse;
import internet_store.lesson_3.core.services.validators.FindByIdValidator;
import internet_store.lesson_3.database.Database;

import java.util.List;

public class FindByIdService {

    private final Database database;
    private final FindByIdValidator validator;


    public FindByIdService(Database database, FindByIdValidator validator) {
        this.database = database;
        this.validator = validator;
    }


    public FindByIdResponse findById(FindByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = Long.parseLong(request.getProductId());

        if (!errors.isEmpty()){
            return new FindByIdResponse(errors);
        } else return new FindByIdResponse(database.findById(id));
    }

}

