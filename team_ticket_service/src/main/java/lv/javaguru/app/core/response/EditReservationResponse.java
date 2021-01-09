package lv.javaguru.app.core.response;

import java.util.List;

public class EditReservationResponse extends Response {
    public EditReservationResponse() {
    }

    public EditReservationResponse(List<CodeError> errorList) {
        super(errorList);
    }
}
