package internet_store.database.customer;

import internet_store.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDatabase {

    List <Customer> getCustomers();

    void addCustomer(Customer customer);

    boolean deleteCustomerById(Long id);

    Optional<Customer> findById(Long id);

    List<Customer> findCustomersByNameAndSurname(String name, String surname);

    List<Customer> findAllCustomersByName(String name);

    List<Customer> findAllCustomersBySurname(String surname);

    boolean containsCustomer (Customer customer);

    boolean containsId (Long id);
}
