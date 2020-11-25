package internet_store.database.customer;

import internet_store.core.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerDatabaseImpl implements CustomerDatabase{

    private Long id = 1L;
    private List<Customer> customerList = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customerList;
    }

    @Override
    public boolean addCustomer(Customer customer){
        if(customerList.contains(customer)){
            return false;
        } else {
            customer.setId(id);
            customerList.add(customer);
            id++;
        }
        return true;
    }

    @Override
    public void deleteCustomer(long id){
        customerList.removeIf(customer -> customer.getId() == id);

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
}
