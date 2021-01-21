package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.services.validators.RegisterRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.List;

public class RegisterService {
    private final UserDatabase userDatabase;

    private final RegisterRequestValidator validator;

    public RegisterService(UserDatabase userDatabase, RegisterRequestValidator validator) {
        this.userDatabase = userDatabase;
        this.validator = validator;
    }

    public RegistrationResponse execute(RegistrationRequest request) {
        List<CodeError> errors = validator.validate(request);

        if (!errors.isEmpty())
            return new RegistrationResponse(errors);

        request.getUser().setPersonType(PersonType.CLIENT);
        userDatabase.addUser(request.getUser());

        return new RegistrationResponse();
    }
}
