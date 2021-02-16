package internet_store.application.core.requests.customer;

public class GetCustomerRequest {

    private Long id;

    public GetCustomerRequest() {
    }

    public GetCustomerRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
