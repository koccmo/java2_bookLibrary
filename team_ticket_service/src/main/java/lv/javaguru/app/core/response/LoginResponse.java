package lv.javaguru.app.core.response;

import java.util.List;

public class LoginResponse extends Response{
    public LoginResponse() {
    }

    public LoginResponse(List<CodeError> errorList) {
        super(errorList);
    }

}
