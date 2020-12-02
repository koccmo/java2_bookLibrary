package internet_store.core.requests.customer;

public class FindCustomerByIdRequest {

    private Long id;

    public FindCustomerByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
