package internet_store.database.customer;

import internet_store.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDatabaseImpl implements CustomerDatabase{

    private Long id = 1L;
    private List<Customer> customerList = new ArrayList<>();

    public List<Customer> getCustomerList() {
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
    public boolean printCustomersInfo(){
        if (customerList.size() > 0) {
            System.out.println("Customers in database: ");
            customerList.forEach(System.out::println);
            return true;
        } else {
            System.out.println("Customer database is empty!");
            return false;
        }
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
        List<Customer> listOfEqualCustomersNames = new ArrayList<>();
        for (Customer customer : customerList){
            if (customer.equals(name)){
                customerList.add(customer);
            }
        }
        return listOfEqualCustomersNames;
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
