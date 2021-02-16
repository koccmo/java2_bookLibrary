package lv.estore.app.core.request.users;

public class UserIdRequest {

    private String id;

    public UserIdRequest() {
    }

    public UserIdRequest(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
