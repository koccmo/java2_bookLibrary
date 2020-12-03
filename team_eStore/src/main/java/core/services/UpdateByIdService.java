package core.services;

import core.request.CoreRequest;
import core.responses.CoreResponse;
import core.responses.Errors;
import core.responses.UpdateResponse;
import core.validators.iValidator;
import repository.iDatabase;

public class UpdateByIdService implements iService{

    private iDatabase database;
    private iValidator validator;

    public UpdateByIdService(final iDatabase database, final iValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    /**
     * Method to update product info by ID.
     * @param request CoreRequest
     * @return CoreResponse
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        final Errors errors = validator.validate(request);
        if (errors.getAllErrors().isEmpty()) {
            response = new UpdateResponse(database.updateById(request.getId(),
                                                    request.getName(),
                                                    request.getDescription(),
                                                    request.getPrice()));
        } else {
            response = new UpdateResponse(errors);
        }
        return response;
    }
}
