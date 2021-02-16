package lv.estore.app.core.responses.users;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class UpdateUserResponse extends CoreResponse {
    private boolean userUpdated;

    public UpdateUserResponse(boolean userUpdated) {
        this.userUpdated = userUpdated;
    }

    public UpdateUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isUserUpdated() {
        return userUpdated;
    }
}
