package internet_store.application.core.requests.product;

public class GetProductRequest {

    private Long id;

    public GetProductRequest() {
    }

    public GetProductRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
