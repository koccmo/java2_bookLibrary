package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.validators.RegisterRequestValidator;
import lv.javaguru.app.database.Database;

import java.util.List;

public class RegisterService {
    private final Database database;
    private final RegisterRequestValidator validator;

    public RegisterService(Database database, RegisterRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public RegistrationResponse execute(RegistrationRequest request) {
        List<CodeError> errors = validator.validate(request);

        if (!errors.isEmpty())
            return new RegistrationResponse(errors);

        request.getPerson().setPersonType(PersonType.CLIENT);
        database.addPerson(request.getPerson());

        return new RegistrationResponse();
    }
}
