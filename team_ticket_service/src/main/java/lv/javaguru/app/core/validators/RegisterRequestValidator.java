package lv.javaguru.app.core.validators;

import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.CodeError;

import java.util.ArrayList;
import java.util.List;

public class RegisterRequestValidator {
    public RegisterRequestValidator() {
    }

    public List<CodeError> validate(RegistrationRequest request) {
        List<CodeError> errorList = new ArrayList<>();

        if (request.getPerson().getName() == null || request.getPerson().getName().equals("")) {
            CodeError error = new CodeError("personsName", "Wrong name!");
            errorList.add(error);
        }

        if (request.getPerson().getSurname() == null || request.getPerson().getSurname().equals("")) {
            CodeError error = new CodeError("personsSurname", "Wrong surname!");
            errorList.add(error);
        }

        return errorList;
    }
}
