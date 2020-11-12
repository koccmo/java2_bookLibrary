package internet_store.database.customer;

import internet_store.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDatabase {

    public List <Customer> getCustomers();

    boolean addCustomer(Customer customer);

    void deleteCustomer(long id);

    Optional<Customer> findCustomersByNameAndSurname(String name, String surname);

    List<Customer> findAllCustomersByName(String name);

    List<Customer> findAllCustomersBySurname(String surname);
}
