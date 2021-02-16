package lv.estore.app.core.responses.passwords;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class PasswordResponse extends CoreResponse {

    private boolean passwordAdded;

    public PasswordResponse(boolean passwordAdded) {
        this.passwordAdded = passwordAdded;
    }

    public PasswordResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isPasswordAdded() {
        return passwordAdded;
    }

    public void setPasswordAdded(boolean passwordAdded) {
        this.passwordAdded = passwordAdded;
    }
}
