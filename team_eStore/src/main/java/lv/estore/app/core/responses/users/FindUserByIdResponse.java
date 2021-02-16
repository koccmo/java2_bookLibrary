package lv.estore.app.core.responses.users;

import lv.estore.app.core.domain.User;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class FindUserByIdResponse extends CoreResponse {
    private User user;

    public FindUserByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindUserByIdResponse(final User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
