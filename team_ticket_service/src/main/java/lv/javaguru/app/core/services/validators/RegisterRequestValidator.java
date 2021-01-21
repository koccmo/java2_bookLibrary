package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.CodeError;

import java.util.ArrayList;
import java.util.List;

public class RegisterRequestValidator {
    public RegisterRequestValidator() {
    }

    public List<CodeError> validate(RegistrationRequest request) {
        List<CodeError> errorList = new ArrayList<>();

        if (request.getUser().getName() == null || request.getUser().getName().equals("")) {
            CodeError error = new CodeError("personsName", "Wrong name!");
            errorList.add(error);
        }

        if (request.getUser().getSurname() == null || request.getUser().getSurname().equals("")) {
            CodeError error = new CodeError("personsSurname", "Wrong surname!");
            errorList.add(error);
        }

        return errorList;
    }
}
