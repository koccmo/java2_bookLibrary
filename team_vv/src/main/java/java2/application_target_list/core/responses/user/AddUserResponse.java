package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class AddUserResponse extends UserCoreResponse{

    private User newUser;

    public AddUserResponse(User newUser) {
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public AddUserResponse(List<CoreError> errorList) {
        super(errorList);
    }
}
