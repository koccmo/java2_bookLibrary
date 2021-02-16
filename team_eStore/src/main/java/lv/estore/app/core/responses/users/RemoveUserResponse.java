package lv.estore.app.core.responses.users;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class RemoveUserResponse extends CoreResponse {

    private boolean userRemoved;

    public RemoveUserResponse(boolean userRemoved) {
        this.userRemoved = userRemoved;
    }

    public RemoveUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isUserRemoved() {
        return userRemoved;
    }
}
