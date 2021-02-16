package java2.application_target_list.core.requests.user;

public class DeleteUserRequest {

    private Long userIdToDelete;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(Long userIdToDelete) {
        this.userIdToDelete = userIdToDelete;
    }

    public Long getUserIdToDelete() {
        return userIdToDelete;
    }

    public void setUserIdToDelete(Long userIdToDelete) {
        this.userIdToDelete = userIdToDelete;
    }
}

