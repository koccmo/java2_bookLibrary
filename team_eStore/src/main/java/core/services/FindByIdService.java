package core.services;

import core.request.CoreRequest;
import core.responses.CoreResponse;
import core.responses.Errors;
import core.responses.FindResponse;
import core.validators.iValidator;
import repository.iDatabase;

public class FindByIdService implements iService {

    private iValidator validator;
    private iDatabase database;

    public FindByIdService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /**
     * Method to find product by 'Id'.
     * @param request CoreRequest
     * @return response CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);

        if (errors.getAllErrors().isEmpty()) {
            response = new FindResponse(database.findById(request.getId()));
        } else {
            response = new FindResponse(errors);
        }
        return response;
    }
}
