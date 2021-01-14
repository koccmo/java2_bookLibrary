package java2.application_target_list.core.responses.user;


import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class SearchUserByFirstNameResponse extends UserCoreResponse{

    private List<User> usersList;

    public SearchUserByFirstNameResponse(List<CoreError> errorList, List<User> usersList) {
        super(errorList);
        this.usersList = usersList;
    }


    public List<User> getUsersList() {
        return usersList;
    }
}
