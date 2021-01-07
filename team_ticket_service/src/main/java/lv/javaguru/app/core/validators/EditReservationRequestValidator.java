package lv.javaguru.app.core.validators;

import lv.javaguru.app.core.request.AddReservationRequest;
import lv.javaguru.app.core.request.EditReservationRequest;
import lv.javaguru.app.core.response.CodeError;

import java.util.ArrayList;
import java.util.List;

public class EditReservationRequestValidator {
    public EditReservationRequestValidator() {
    }

    public List<CodeError> validate(EditReservationRequest request) {
        List<CodeError> errorList = new ArrayList<>();


        return errorList;
    }
}
