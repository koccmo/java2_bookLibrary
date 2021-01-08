package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.LoginRequest;
import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.LoginResponse;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.validators.AddReservationRequestValidator;
import lv.javaguru.app.core.validators.LoginRequestValidator;
import lv.javaguru.app.database.Database;

import java.util.List;

public class LoginService {

    private final Database database;
    private final LoginRequestValidator validator;

    public LoginService(Database database, LoginRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public LoginResponse execute(LoginRequest request) {
        List<CodeError> errors = validator.validate(request);

        if (!errors.isEmpty())
            return new LoginResponse(errors);

        else if (!database.containsPerson(request.getPerson())) {
            errors.add(new CodeError("xxx", "you are not registered!"));
            return new LoginResponse(errors);
        }
        database.setCurrentPerson(request.getPerson());
        return new LoginResponse();
    }
}
