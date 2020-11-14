package lv.javaguru.app.core.response;

import java.util.List;

public class AddReservationResponse extends  Response{

    public AddReservationResponse() {
    }

    public AddReservationResponse(List<CodeError> errorList) {
        super(errorList);
    }
}
