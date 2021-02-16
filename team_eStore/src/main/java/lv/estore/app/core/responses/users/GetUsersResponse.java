package lv.estore.app.core.responses.users;

import lv.estore.app.core.domain.User;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class GetUsersResponse extends CoreResponse {
    private List<User> users;

    public GetUsersResponse(List<CoreError> errors, List<User> users) {
        super(errors);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean hasUsers() {
        return users != null && !users.isEmpty();
    }
}
