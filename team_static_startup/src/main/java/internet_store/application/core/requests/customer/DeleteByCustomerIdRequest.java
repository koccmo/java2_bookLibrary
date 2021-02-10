package internet_store.application.core.requests.customer;

public class DeleteByCustomerIdRequest {

    private Long customerId;

    public DeleteByCustomerIdRequest() {
    }

    public DeleteByCustomerIdRequest(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
