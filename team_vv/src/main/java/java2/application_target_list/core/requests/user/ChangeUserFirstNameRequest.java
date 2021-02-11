package java2.application_target_list.core.requests.user;

public class ChangeUserFirstNameRequest {

    private Long userIdToChange;
    private String newUserFirstName;

    public ChangeUserFirstNameRequest() {
    }

    public ChangeUserFirstNameRequest(Long userIdToChange, String newUserFirstName) {
        this.userIdToChange = userIdToChange;
        this.newUserFirstName = newUserFirstName;
    }

    public Long getUserIdToChange() {
        return userIdToChange;
    }

    public String getNewUserFirstName() {
        return newUserFirstName;
    }

    public void setUserIdToChange(Long userIdToChange) {
        this.userIdToChange = userIdToChange;
    }

    public void setNewUserFirstName(String newUserFirstName) {
        this.newUserFirstName = newUserFirstName;
    }
}
