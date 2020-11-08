package internet_store.database.customer;

import internet_store.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDatabase {

    boolean addCustomer(Customer customer);

    boolean deleteCustomer(long id);

    boolean printCustomersInfo();

    Optional<Customer> findCustomersByNameAndSurname(String name, String surname);

    List<Customer> findAllCustomersByName(String name);

    List<Customer> findAllCustomersBySurname(String surname);
}
