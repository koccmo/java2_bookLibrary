package java2.application_target_list.core.requests.user;

public class ChangeUserLastNameRequest {

    private Long userIdToChange;
    private String newUserLastName;

    public ChangeUserLastNameRequest(Long userIdToChange, String newUserLastName) {
        this.userIdToChange = userIdToChange;
        this.newUserLastName = newUserLastName;
    }

    public Long getUserIdToChange() {
        return userIdToChange;
    }

    public String getNewUserLastName() {
        return newUserLastName;
    }
}
