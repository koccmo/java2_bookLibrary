package java2.application_target_list.core.requests.user;

public class GetUserRequest {

    private Long id;

    public GetUserRequest(Long id) {
        this.id = id;
    }

    public GetUserRequest() {
    }

    public Long getId() {
        return id;
    }
}
