package java2.application_target_list.core.requests.board;

public class GetRecordRequest {

    private Long id;

    public GetRecordRequest() {
    }

    public GetRecordRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
