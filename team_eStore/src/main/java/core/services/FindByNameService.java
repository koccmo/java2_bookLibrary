package core.services;

import core.request.CoreRequest;
import core.responses.CoreResponse;
import core.responses.Errors;
import core.responses.FindResponse;
import core.validators.iValidator;
import repository.iDatabase;

public class FindByNameService implements iService {

    private iDatabase database;
    private iValidator validator;

    public FindByNameService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /**
     * Method to find product by name.
     * @param request CoreRequest
     * @return CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            response = new FindResponse(database.findByName(request.getName()));
        } else {
            response = new FindResponse(errors);
        }
        return response;
    }
}
