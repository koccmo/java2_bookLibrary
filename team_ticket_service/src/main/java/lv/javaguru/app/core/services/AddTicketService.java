package lv.javaguru.app.core.services;

import lv.javaguru.app.database.Database;
import lv.javaguru.app.core.request.AddTicketRequest;
import lv.javaguru.app.core.services.validators.AddTicketRequestValidator;
import lv.javaguru.app.core.response.AddTicketResponse;
import lv.javaguru.app.core.response.CodeError;

import java.util.List;

public class AddTicketService {

    private final Database database;
    private final AddTicketRequestValidator validator;

    public AddTicketService (Database database, AddTicketRequestValidator validator) {
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
