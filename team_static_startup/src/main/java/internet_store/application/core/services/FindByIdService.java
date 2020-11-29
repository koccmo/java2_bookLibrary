package internet_store.application.core.services;

import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.FindByIdResponse;
import internet_store.application.core.services.validators.FindByIdValidator;
import internet_store.application.core.database.Database;

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
        Long id = Long.parseLong(request.getProductId());

        if (!errors.isEmpty()){
            return new FindByIdResponse(errors);
        } else return new FindByIdResponse(database.findById(id));
    }

}

