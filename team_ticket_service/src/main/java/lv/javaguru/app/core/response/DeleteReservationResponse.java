package lv.javaguru.app.core.response;

import java.util.List;

public class DeleteReservationResponse extends Response {

    public DeleteReservationResponse() {
    }

    public DeleteReservationResponse(List<CodeError> errorList) {
        super(errorList);
    }
}
