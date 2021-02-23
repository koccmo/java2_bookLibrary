package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class AddFlightResponse extends  Response{

    public AddFlightResponse () {
    }

    public AddFlightResponse (List<CodeError> errorList) {
        super(errorList);
    }
}
