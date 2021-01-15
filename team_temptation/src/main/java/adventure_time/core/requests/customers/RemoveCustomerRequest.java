package adventure_time.core.requests.customers;

public class RemoveCustomerRequest {

    private final Long customerId;

    public RemoveCustomerRequest(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
