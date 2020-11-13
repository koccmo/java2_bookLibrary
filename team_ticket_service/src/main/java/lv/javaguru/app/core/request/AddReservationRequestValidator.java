package lv.javaguru.app.core.request;

import lv.javaguru.app.core.request.AddReservationRequest;
import lv.javaguru.app.core.response.CodeError;

import java.util.ArrayList;
import java.util.List;

public class AddReservationRequestValidator {

    public AddReservationRequestValidator() {
    }

    public List<CodeError> validate(AddReservationRequest request) {
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
