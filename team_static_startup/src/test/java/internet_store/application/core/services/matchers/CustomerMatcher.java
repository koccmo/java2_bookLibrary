package internet_store.application.core.services.matchers;

import internet_store.application.core.domain.Customer;
import org.mockito.ArgumentMatcher;

public class CustomerMatcher implements ArgumentMatcher<Customer> {
    private Long customerId;
    private String customerFirstName;
    private String customerSecondName;

    public CustomerMatcher(String customerFirstName, String customerSecondName) {
        this.customerFirstName = customerFirstName;
        this.customerSecondName = customerSecondName;

    }

    @Override
    public boolean matches(Customer customer) {
        return customer.getCustomerFirstName().equals(customerFirstName)
                && customer.getCustomerSecondName().equals(customerSecondName);
     }

}
