package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class FlightAddResponse extends  Response{

    public FlightAddResponse () {
    }

    public FlightAddResponse (List<CodeError> errorList) {
        super(errorList);
    }
}
