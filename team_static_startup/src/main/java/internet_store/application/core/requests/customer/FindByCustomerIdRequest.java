package internet_store.application.core.requests.customer;

public class FindByCustomerIdRequest {

    private String customerId;

    public FindByCustomerIdRequest() {
    }

    public FindByCustomerIdRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
