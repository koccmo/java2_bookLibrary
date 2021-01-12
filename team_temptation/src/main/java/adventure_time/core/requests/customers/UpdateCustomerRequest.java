package adventure_time.core.requests.customers;

import java.util.Objects;

public class UpdateCustomerRequest {

    private final Long customerID;

    public UpdateCustomerRequest(Long customerID) {
        this.customerID = customerID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCustomerRequest that = (UpdateCustomerRequest) o;
        return Objects.equals(customerID, that.customerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }
}
