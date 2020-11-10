package internet_store;

import internet_store.database.customer.CustomerDatabaseImpl;
import internet_store.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CustomerDatabaseImplTest {

    CustomerDatabaseImpl customerDatabase = new CustomerDatabaseImpl();

    Customer firstCustomer = new Customer(
            "Mihail", "Galkin",
            27643078, "Saharova 31-24",
            "janegei@gmail.com");
    Customer secondCustomer = new Customer(
            "Savva", "Jablokov",
            27446291, "Kamiela 42-77",
            "kakdela@gmail.com");
    Customer thirdCustomer = new Customer(
            "Anton", "Moiseev",
            28446521, "Brivibas 33-11",
            "assa@gmail.com");
    Customer fourthCustomer = new Customer("Mihail", "Glinka",
            27643991, "Stabu 15-12",
            "pianino@gmail.com");

    @Before
    public void init(){
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
    }

    @Test
    public void addCustomerTest(){

        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
        assertTrue(customerDatabase.getCustomers().contains(secondCustomer));
    }


    @Test
    public void deleteCustomerTest(){

        customerDatabase.deleteCustomer(1L);
        assertFalse(customerDatabase.getCustomers().contains(firstCustomer));
        assertTrue(customerDatabase.getCustomers().contains(secondCustomer));
    }


    @Test
    public void deleteCustomerTestNoCustomerInDatabase(){

        customerDatabase.getCustomers().size();
        customerDatabase.deleteCustomer(3);
        assertTrue(customerDatabase.getCustomers().contains(firstCustomer));
    }

    @Test
    public void getCustomerListTest(){

        List<Customer> listOfAllCustomers = customerDatabase.getCustomers();

        assertTrue(listOfAllCustomers.contains(firstCustomer));
        assertTrue(listOfAllCustomers.contains(secondCustomer));
        assertFalse(listOfAllCustomers.size() > 2);
    }

    @Test
    public void findAnyCustomerByNameAndSurnameTest(){

        customerDatabase.addCustomer(thirdCustomer);

        Optional<Customer> resultOfFinding1 = customerDatabase.findCustomersByNameAndSurname
                ("Mihail", "Galkin");
        Optional<Customer> resultOfFinding2 = customerDatabase.findCustomersByNameAndSurname(
                "Savva", "Jablokov");
        Optional<Customer> resultOfFinding3 = customerDatabase.findCustomersByNameAndSurname(
                "Anton", "Moiseev");
        Optional<Customer> resultOfFinding4 = customerDatabase.findCustomersByNameAndSurname(
                "Jaroslav", "Brutan");

        assertTrue(resultOfFinding1.isPresent());
        assertTrue(resultOfFinding2.isPresent());
        assertTrue(resultOfFinding3.isPresent());
        assertFalse(resultOfFinding4.isPresent());
    }

    @Test
    public void findAllCustomersByNameTest(){

        customerDatabase.addCustomer(thirdCustomer);
        customerDatabase.addCustomer(fourthCustomer);

        List<Customer> resultOfAllFindings1 = customerDatabase.findAllCustomersByName("Mihail");
        List<Customer> resultOfAllFindings2 = customerDatabase.findAllCustomersByName("Savva");
        List<Customer> resultOfAllFindings3 = customerDatabase.findAllCustomersByName("Anton");
        List<Customer> resultOfAllFindings4 = customerDatabase.findAllCustomersByName("Mihail");

        assertTrue(resultOfAllFindings1.contains(firstCustomer));
        assertFalse(resultOfAllFindings2.contains(thirdCustomer));
    }

}
