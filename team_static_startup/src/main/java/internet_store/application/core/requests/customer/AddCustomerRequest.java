package internet_store.application.core.requests.customer;

public class AddCustomerRequest {

    private String customerFirstName;
    private String customerSecondName;

    public AddCustomerRequest(String customerFirstName, String customerSecondName) {
        this.customerFirstName = customerFirstName;
        this.customerSecondName = customerSecondName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerSecondName() {
        return customerSecondName;
    }

}
