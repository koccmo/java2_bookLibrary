package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.domain.User;

import java.util.List;

public class GetAllUsersResponse extends UserCoreResponse{

    private List<User> usersList;

    public GetAllUsersResponse(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<User> getUsersList() {
        return usersList;
    }
}
