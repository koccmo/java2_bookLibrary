package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.request.AddReservationRequest;
import lv.javaguru.app.core.request.AddReservationRequestValidator;
import lv.javaguru.app.core.response.AddReservationResponse;
import lv.javaguru.app.core.response.CodeError;

import java.util.List;

public class AddReservationService {

    private final Database database;
    private final AddReservationRequestValidator validator;

    public AddReservationService(Database database, AddReservationRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }


    public AddReservationResponse execute(AddReservationRequest request) {
        List<CodeError> errors = validator.validate(request);

        if (!errors.isEmpty())
            return new AddReservationResponse(errors);

        database.add(request.getPerson(), request.getTicket());

        return new AddReservationResponse();
    }
}
