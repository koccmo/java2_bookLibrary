package internet_store.application.core.database.customer;

import internet_store.application.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Long addCustomer(Customer customer);

    boolean deleteByCustomerId(Long customerId);

    boolean deleteCustomer(Customer customer);

    boolean deleteByCustomerFirstName(String customer);

    boolean deleteByCustomerSecondName(String customer);

    List<Customer> findByCustomerFirstName(String customerName);

    List<Customer> findByCustomerSecondName(String customerName);

    List<Customer> findByFirstNameAndSecondName(String firstName, String secondName);

    List<Customer> getCustomerList();

    Optional<Customer> findByCustomerId(Long id);

    boolean changeCustomerFirstName(Long id, String newFirstName);

    boolean changeCustomerSecondName(Long id, String newSecondName);

}
