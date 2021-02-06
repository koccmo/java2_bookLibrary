package internet_store.database.customer;

import internet_store.core.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class CustomerDatabaseImpl implements CustomerDatabase{

    private Long id = 1L;
    private final List<Customer> customerList = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customerList;
    }

    @Override
    public void addCustomer(Customer customer){
            customer.setId(id);
            customerList.add(customer);
            id++;
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        if (customerList.removeIf(customer -> customer.getId().equals(id))) {
            return false;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Customer> findById(Long id){
        return customerList.stream()
                .filter(customer -> customer.getId() == id)
                .findAny();
    }

    @Override
    public List<Customer> findCustomersByNameAndSurname(String name, String surname){
       return customerList.stream()
               .filter(customer -> customer.getName().equals(name)
               && customer.getSurname().equals(surname))
               .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomersByName(String name){
        return customerList.stream()
                .filter(customer -> customer.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomersBySurname(String surname) {
        return customerList.stream()
                .filter(customer -> customer.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public boolean containsCustomer(Customer customer){
        return customerList.stream()
                .anyMatch(customer1 -> customer1.equals(customer));
    }

    @Override
    public boolean containsId(Long id){
        return customerList.stream()
                .anyMatch(customer -> customer.getId() == id);
    }
}