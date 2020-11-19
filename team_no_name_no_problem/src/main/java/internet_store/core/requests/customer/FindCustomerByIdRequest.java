package internet_store.core.requests.customer;

public class FindCustomerByIdRequest {

    private long id;

    public FindCustomerByIdRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
