package java2.application_target_list.core.requests.user;

public class AddUserRequest {

    private String firstName;
    private String lastName;

    public AddUserRequest() {
    }

    public AddUserRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
