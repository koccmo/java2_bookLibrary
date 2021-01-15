package java2.application_target_list.core.responses.user;

import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class DeleteUserResponse extends UserCoreResponse {

    private Long userIdToDelete;

    public DeleteUserResponse(Long userIdToDelete) {
        this.userIdToDelete = userIdToDelete;
    }

    public Long getUserIdToDelete() {
        return userIdToDelete;
    }

    public DeleteUserResponse(List<CoreError> errorList) {
        super(errorList);
    }

}
