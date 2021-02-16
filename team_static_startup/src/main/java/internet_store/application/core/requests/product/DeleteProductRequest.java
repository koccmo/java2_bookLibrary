package internet_store.application.core.requests.product;

public class DeleteProductRequest {

    private Long id;

    public DeleteProductRequest() { }

    public DeleteProductRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
