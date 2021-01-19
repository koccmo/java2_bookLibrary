package internet_store_tests.database.product;

import internet_store.database.customer.CustomerDatabaseImpl;
import internet_store.core.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerDatabaseImplTest {

    CustomerDatabaseImpl customerDatabase = new CustomerDatabaseImpl();

    Customer firstCustomer = new Customer(
            "Mihail", "Galkin",
            "27643078", "Saharova 31-24",
            "janegei@gmail.com");
    Customer secondCustomer = new Customer(
            "Savva", "Jablokov",
            "27446291", "Kamiela 42-77",
            "kakdela@gmail.com");
    Customer thirdCustomer = new Customer(
            "Anton", "Moiseev",
            "28446521", "Brivibas 33-11",
            "assa@gmail.com");
    Customer fourthCustomer = new Customer("Mihail", "Glinka",
            "27643991", "Stabu 15-12",
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

        customerDatabase.deleteCustomerById(1L);
        assertFalse(customerDatabase.getCustomers().contains(firstCustomer));
        assertTrue(customerDatabase.getCustomers().contains(secondCustomer));
    }


    @Test
    public void deleteCustomerTestNoCustomerInDatabase(){

        customerDatabase.getCustomers().size();
        customerDatabase.deleteCustomerById(3L);
        customerDatabase.getCustomers().size();
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

        List<Customer> resultOfFinding1 = customerDatabase.findCustomersByNameAndSurname
                ("Mihail", "Galkin");
        List<Customer> resultOfFinding2 = customerDatabase.findCustomersByNameAndSurname(
                "Savva", "Jablokov");
        List<Customer> resultOfFinding3 = customerDatabase.findCustomersByNameAndSurname(
                "Anton", "Moiseev");
        List<Customer> resultOfFinding4 = customerDatabase.findCustomersByNameAndSurname(
                "Jaroslav", "Brutan");

        assertTrue(resultOfFinding1.get(0).getName().equals("Mihail"));
        assertTrue(resultOfFinding2.get(0).getSurname().equals("Jablokov"));
        assertTrue(resultOfFinding3.size() == 1);
        assertTrue(resultOfFinding4.isEmpty());
    }

    @Test
    public void findAllCustomersByNameTest(){

        customerDatabase.addCustomer(thirdCustomer);

        List<Customer> resultOfAllEqualNames1 = customerDatabase.findAllCustomersByName("Mihail");
        List<Customer> resultOfAllEqualNames2 = customerDatabase.findAllCustomersByName("Savva");
        List<Customer> resultOfAllEqualNames3 = customerDatabase.findAllCustomersByName("Anton");

        assertTrue(resultOfAllEqualNames1.contains(firstCustomer));
        assertTrue(resultOfAllEqualNames3.contains(thirdCustomer));
        assertFalse(resultOfAllEqualNames1.contains(secondCustomer));
        assertFalse(resultOfAllEqualNames2.contains(thirdCustomer));
    }

    @Test
    public void findAllCustomersBySurnameTest(){

        customerDatabase.addCustomer(thirdCustomer);

        List<Customer> resultOfAllEqualSurnames1 = customerDatabase.findAllCustomersBySurname("Galkin");
        List<Customer> resultOfAllEqualSurnames2 = customerDatabase.findAllCustomersBySurname("Jablokov");
        List<Customer> resultOfAllEqualSurnames3 = customerDatabase.findAllCustomersBySurname("Moiseev");

        assertTrue(resultOfAllEqualSurnames1.contains(firstCustomer));
        assertTrue(resultOfAllEqualSurnames2.contains(secondCustomer));
        assertTrue(resultOfAllEqualSurnames3.contains(thirdCustomer));
        assertFalse(resultOfAllEqualSurnames1.contains(secondCustomer));
        assertFalse(resultOfAllEqualSurnames2.contains(thirdCustomer));
        assertFalse(resultOfAllEqualSurnames3.contains(firstCustomer));
    }


}
