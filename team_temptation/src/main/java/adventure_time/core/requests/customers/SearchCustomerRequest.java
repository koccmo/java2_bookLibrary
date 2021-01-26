package adventure_time.core.requests.customers;

public class SearchCustomerRequest {

    private Long customerID;
    private String customerEmail;

    public SearchCustomerRequest(Long customerID, String customerEmail) {
        this.customerID = customerID;
        this.customerEmail = customerEmail;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }


}
