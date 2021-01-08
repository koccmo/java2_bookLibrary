package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.AddReservationRequest;
import lv.javaguru.app.core.request.EditReservationRequest;
import lv.javaguru.app.core.response.AddReservationResponse;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.EditReservationResponse;
import lv.javaguru.app.core.validators.AddReservationRequestValidator;
import lv.javaguru.app.core.validators.EditReservationRequestValidator;
import lv.javaguru.app.database.Database;

import java.util.List;

public class EditReservationService {
    private final Database database;
    private final EditReservationRequestValidator validator;


    public EditReservationService(Database database, EditReservationRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }


    public EditReservationResponse execute(EditReservationRequest request) {
        List<CodeError> errors = validator.validate(request);

        if (!errors.isEmpty())
            return new EditReservationResponse(errors);

        database.add(request.getPerson(), request.getTicket());

        return new EditReservationResponse();
    }
}
