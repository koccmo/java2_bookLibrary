package internet_store.application.core.requests.customer;

public class ChangeCustomerFirstNameRequest {

    private Long id;
    private String customerNewName;

    public ChangeCustomerFirstNameRequest(Long id, String customerNewName) {
        this.id = id;
        this.customerNewName = customerNewName;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerNewName() {
        return customerNewName;
    }

}
