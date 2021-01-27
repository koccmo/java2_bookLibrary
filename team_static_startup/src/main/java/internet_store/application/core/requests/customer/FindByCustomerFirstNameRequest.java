package internet_store.application.core.requests.customer;

public class FindByCustomerFirstNameRequest {

    private String customerFirstName;

    public FindByCustomerFirstNameRequest(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

}
