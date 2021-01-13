package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.request.AddTicketRequest;
import lv.javaguru.app.core.validators.AddReservationRequestValidator;
import lv.javaguru.app.core.response.AddTicketResponse;
import lv.javaguru.app.core.response.CodeError;

import java.util.List;

public class AddReservationService {

    private final Database database;
    private final AddReservationRequestValidator validator;

    public AddReservationService(Database database, AddReservationRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }


    public AddTicketResponse execute(AddTicketRequest request) {
        List<CodeError> errors = validator.validate(request);

        if (!errors.isEmpty())
            return new AddTicketResponse(errors);

        database.addTicket(request.getCurrUser(), request.getTicket());

        return new AddTicketResponse();
    }
}
