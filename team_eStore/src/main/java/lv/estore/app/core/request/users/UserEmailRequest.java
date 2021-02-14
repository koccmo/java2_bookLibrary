package lv.estore.app.core.request.users;

public class UserEmailRequest {

    private String email;

    public UserEmailRequest() {
    }

    public UserEmailRequest(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
