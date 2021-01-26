package internet_store.application.core.requests.customer;

public class DeleteByCustomerIdRequest {

    private Long customerId;

    public DeleteByCustomerIdRequest(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
