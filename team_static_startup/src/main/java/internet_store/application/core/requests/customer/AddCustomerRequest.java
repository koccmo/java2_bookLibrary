package internet_store.application.core.requests.customer;

public class AddCustomerRequest {

    private String customerFirstName;
    private String customerSecondName;
    private String customerPhone;

    public AddCustomerRequest(String customerFirstName, String customerSecondName, String customerPhone) {
        this.customerFirstName = customerFirstName;
        this.customerSecondName = customerSecondName;
        this.customerPhone = customerPhone;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerSecondName() {
        return customerSecondName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

}
