package internet_store.core.requests.customer;

public class FindCustomerByIdRequest {

    private Long id;

    public FindCustomerByIdRequest() {}

    public FindCustomerByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
