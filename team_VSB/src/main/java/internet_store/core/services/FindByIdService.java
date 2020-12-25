package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.requests.FindProductByIDRequest;
import internet_store.core.responses.CoreError;
import internet_store.core.responses.FindByIDResponse;
import internet_store.core.services.validators.FindByIDValidator;

import java.util.List;

public class FindByIdService {


    private final Database database;
    private final FindByIDValidator validator;


    public FindByIdService(Database database, FindByIDValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindByIDResponse findById(FindProductByIDRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = Long.parseLong(request.getProductId());

        if(!errors.isEmpty()) {
            return new FindByIDResponse(errors);
        } else return new FindByIDResponse(database.findById(id));
    }
}
