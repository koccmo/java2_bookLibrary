package lv.estore.app.core.request.passwords;

public class PasswordRequest {
    private String password;
    private String userId;

    public PasswordRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
