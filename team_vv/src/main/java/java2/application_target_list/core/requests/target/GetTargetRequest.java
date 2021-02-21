package java2.application_target_list.core.requests.target;

public class GetTargetRequest {

    private Long id;

    public GetTargetRequest() {
    }

    public GetTargetRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
