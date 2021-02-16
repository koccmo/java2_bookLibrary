package lv.estore.app.core.responses.users;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class AddUserResponse extends CoreResponse {

    private boolean userAdded;

    public AddUserResponse(boolean userAdded) {
        this.userAdded = userAdded;
    }

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isUserAdded(){
        return userAdded;
    }
}
