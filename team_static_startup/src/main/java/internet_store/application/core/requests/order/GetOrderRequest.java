package internet_store.application.core.requests.order;

public class GetOrderRequest {

    private Long id;

    public GetOrderRequest() {
    }

    public GetOrderRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
