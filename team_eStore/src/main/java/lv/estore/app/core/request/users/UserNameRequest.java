package lv.estore.app.core.request.users;

public class UserNameRequest {

    private String name;

    public UserNameRequest() {
    }

    public UserNameRequest(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
