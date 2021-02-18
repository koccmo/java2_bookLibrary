package java2.application_target_list.core.requests.user;

public class UpdateUserRequest {

    private Long userIdToChange;
    private String newUserFirstName;
    private String newUserLastName;

    public UpdateUserRequest(Long userIdToChange, String newUserFirstName, String newUserLastName) {
        this.userIdToChange = userIdToChange;
        this.newUserFirstName = newUserFirstName;
        this.newUserLastName = newUserLastName;
    }

    public UpdateUserRequest() {
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
