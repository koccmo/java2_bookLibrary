package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class GetUserResponse extends UserCoreResponse{

    private User user;

    public GetUserResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
