package lv.javaguru.app.core.response;

import java.util.List;

public class AddTicketResponse extends  Response{

    public AddTicketResponse() {
    }

    public AddTicketResponse(List<CodeError> errorList) {
        super(errorList);
    }
}
