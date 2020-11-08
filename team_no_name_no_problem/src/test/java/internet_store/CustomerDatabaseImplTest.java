package internet_store;

import internet_store.database.customer.CustomerDatabaseImpl;
import internet_store.domain.Customer;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CustomerDatabaseImplTest {

    @Test
    public void addCustomerTest(){
        CustomerDatabaseImpl customerDatabase = new CustomerDatabaseImpl();

        Customer firstCustomer = new Customer(
                "Mihail", "Galkin",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");
        Customer secondCustomer = new Customer(
                "Savva", "Jablokov",
                27446291, "Kamiela 42-77",
                "kakdela@gmail.com");
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
        assertTrue(customerDatabase.getCustomers().contains(secondCustomer));
    }

    @Test
    public void addCustomerTestNotValidInput(){
        CustomerDatabaseImpl customerDatabase = new CustomerDatabaseImpl();

        Customer firstCustomer = new Customer(
                "Mihail", "Galkin",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");

        Customer secondCustomer = new Customer(
                "Mihail", "Galkin",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");

        Customer thirdCustomer = new Customer(
                "MIHAIL", "GALKIN",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");
        boolean saveResult1 = customerDatabase.addCustomer(firstCustomer);
        boolean saveResult2 = customerDatabase.addCustomer(secondCustomer);
        assertTrue(saveResult1);
        assertFalse(saveResult2);
        assertFalse(customerDatabase.addCustomer(thirdCustomer));
    }

    @Test
    public void deleteCustomerTest(){
        CustomerDatabaseImpl customerDatabase = new CustomerDatabaseImpl();

        Customer firstCustomer = new Customer(
                "Mihail", "Galkin",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");
        Customer secondCustomer = new Customer(
                "Savva", "Jablokov",
                27446291, "Kamiela 42-77",
                "kakdela@gmail.com");
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
        boolean deleteResult = customerDatabase.deleteCustomer(1L);
        assertTrue(deleteResult);
        assertFalse(customerDatabase.getCustomers().contains(firstCustomer));
        assertTrue(customerDatabase.getCustomers().contains(secondCustomer));
    }

    @Test
    public void deleteCustomerTest2(){
        CustomerDatabaseImpl customerDatabase = new CustomerDatabaseImpl();

        Customer firstCustomer = new Customer(
                "Mihail", "Galkin",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");
        Customer secondCustomer = new Customer(
                "Savva", "Jablokov",
                27446291, "Kamiela 42-77",
                "kakdela@gmail.com");
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
        boolean deleteResult = customerDatabase.deleteCustomer(2L);
        assertTrue(deleteResult);
    }

    @Test
    public void deleteCustomerTestNoCustomerInDatabase(){
        CustomerDatabaseImpl customerDatabase =  new CustomerDatabaseImpl();

        Customer firstCustomer = new Customer(
                "Mihail", "Galkin",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");
        Customer secondCustomer = new Customer(
                "Savva", "Jablokov",
                27446291, "Kamiela 42-77",
                "kakdela@gmail.com");
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
        assertFalse(customerDatabase.deleteCustomer(6L));
    }

    @Test
    public void getCustomerListTest(){
        CustomerDatabaseImpl customerDatabase = new CustomerDatabaseImpl();

        Customer firstCustomer = new Customer(
                "Mihail", "Galkin",
                27643078, "Saharova 31-24",
                "janegei@gmail.com");
        Customer secondCustomer = new Customer(
                "Savva", "Jablokov",
                27446291, "Kamiela 42-77",
                "kakdela@gmail.com");
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);

        List<Customer> listOfAllCustomers = customerDatabase.getCustomers();

        assertTrue(listOfAllCustomers.contains(firstCustomer));
        assertTrue(listOfAllCustomers.contains(secondCustomer));
        assertFalse(listOfAllCustomers.size() > 2);
    }

    @Test
    public void findAnyCustomerByNameAndSurnameTest(){
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
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
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
        customerDatabase.addCustomer(firstCustomer);
        customerDatabase.addCustomer(secondCustomer);
        customerDatabase.addCustomer(thirdCustomer);

        List<Customer> resultOfAllFindings1 = customerDatabase.findAllCustomersByName("Mihail");
        List<Customer> resultOfAllFindings2 = customerDatabase.findAllCustomersByName("Savva");
        List<Customer> resultOfAllFindings3 = customerDatabase.findAllCustomersByName("Anton");
    }

}
