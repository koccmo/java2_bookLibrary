package internet_store.database.customer;

import internet_store.domain.Customer;
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
    public boolean deleteCustomer(long id){
        for (int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getId() == id){
                customerList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Customer> findCustomersByNameAndSurname(String name, String surname){
        for (Customer customer : customerList) {
            if ((customer.getName().equals(name)) && (customer.getSurname().equals(surname))) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAllCustomersByName(String name){
        return customerList.stream()
                .filter(customer -> customer.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomersBySurname(String surname){
        List<Customer> listOfEqualCustomersSurnames = new ArrayList<>();
        for (Customer customer : customerList){
            if (customer.equals(surname)){
                customerList.add(customer);
            }
        }
        return listOfEqualCustomersSurnames;
    }
}
