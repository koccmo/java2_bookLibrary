package internet_store_tests.core.services_tests.matchers;

import internet_store.core.domain.Customer;
import org.mockito.ArgumentMatcher;

public class CustomerMatcher implements ArgumentMatcher<Customer> {

    private final String name;
    private final String surname;
    private final String phoneNumber;

    public CustomerMatcher(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean matches(Customer customer) {
        return customer.getName().equals(name) &&
                customer.getSurname().equals(surname) &&
                customer.getPhoneNumber().equals(phoneNumber);
    }
}
