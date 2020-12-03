package core.services;

import core.request.CoreRequest;
import core.responses.CoreResponse;
import core.responses.Errors;
import core.responses.RemoveResponse;
import core.validators.iValidator;
import repository.iDatabase;

public class RemoveByNameService implements iService {

    private iDatabase database;
    private iValidator validator;

    public RemoveByNameService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /**
     * Method to remove product by name.
     * @param request CoreRequest
     * @return CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            response = new RemoveResponse(database.removeByName(request.getName()));
        } else {
            response = new RemoveResponse(errors);
        }
        return response;
    }
}
