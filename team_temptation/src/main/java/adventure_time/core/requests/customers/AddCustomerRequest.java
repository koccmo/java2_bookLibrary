package adventure_time.core.requests.customers;

public class AddCustomerRequest {

    private String customerName;
    private String customerEmail;
    private String customerPhone;

    public AddCustomerRequest(String customerName, String customerEmail, String customerPhone) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }
}
