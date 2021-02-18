package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class UpdateUserResponse extends UserCoreResponse{

    private Long userIdToChange;
    private String newUserFirstName;
    private String newUserLastName;

    public UpdateUserResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public UpdateUserResponse(Long userIdToChange, String newUserFirstName, String newUserLastName) {
        this.userIdToChange = userIdToChange;
        this.newUserFirstName = newUserFirstName;
        this.newUserLastName = newUserLastName;
    }

    public Long getUserIdToChange() {
        return userIdToChange;
    }

    public String getNewUserFirstName() {
        return newUserFirstName;
    }

    public String getNewUserLastName() {
        return newUserLastName;
    }
}
