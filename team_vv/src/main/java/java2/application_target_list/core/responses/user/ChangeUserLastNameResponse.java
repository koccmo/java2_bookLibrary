package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class ChangeUserLastNameResponse extends UserCoreResponse{

    private Long userIdToChange;
    private String userNewLastName;

    public ChangeUserLastNameResponse(Long userIdToChange, String userNewLastName) {
        this.userIdToChange = userIdToChange;
        this.userNewLastName = userNewLastName;
    }

    public ChangeUserLastNameResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public Long getUserIdToChange() {
        return userIdToChange;
    }

    public String getUserNewLastName() {
        return userNewLastName;
    }
}
