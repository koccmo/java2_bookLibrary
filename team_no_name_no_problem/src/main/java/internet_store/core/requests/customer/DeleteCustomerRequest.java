package internet_store.core.requests.customer;

public class DeleteCustomerRequest {

    private Long customerByIdRemove;

    public DeleteCustomerRequest(Long customerByIdRemove) {
        this.customerByIdRemove = customerByIdRemove;
    }

    public Long getCustomerByIdRemove() {
        return customerByIdRemove;
    }

    public void setCustomerByIdRemove(Long customerByIdRemove) {
        this.customerByIdRemove = customerByIdRemove;
    }
}
