package internet_store.application.core.requests;

public class DeleteByIdRequest {

    Long id;

    public DeleteByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
