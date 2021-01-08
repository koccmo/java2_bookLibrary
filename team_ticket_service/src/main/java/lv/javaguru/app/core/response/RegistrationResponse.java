package lv.javaguru.app.core.response;

import java.util.List;

public class RegistrationResponse extends Response {
    public RegistrationResponse() {
    }

    public RegistrationResponse(List<CodeError> errorList) {
        super(errorList);
    }

}
