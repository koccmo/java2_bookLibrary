package java2.application_target_list.core.responses.user;


import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class ChangeUserFirstNameResponse extends UserCoreResponse {

    private Long userIdToChange;
    private String userNewFirstName;

    public ChangeUserFirstNameResponse(Long userIdToChange, String userNewFirstName) {
        this.userIdToChange = userIdToChange;
        this.userNewFirstName = userNewFirstName;
    }

    public ChangeUserFirstNameResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public Long getUserIdToChange() {
        return userIdToChange;
    }

    public String getUserNewFirstName() {
        return userNewFirstName;
    }
}
