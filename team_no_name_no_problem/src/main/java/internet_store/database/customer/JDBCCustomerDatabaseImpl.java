package internet_store.database.customer;

import internet_store.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JDBCCustomerDatabaseImpl implements CustomerDatabase{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomers() {
        jdbcTemplate.queryForObject("SELECT * FROM customers", Integer.class);
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {
        jdbcTemplate.update("INSERT INTO customers(name, surname, address, email, phone)"
        + "VALUES (?, ?, ?, ?, ?)",
                customer.getName(), customer.getSurname(), customer.getAddress(), customer.getEmail(),
                customer.getPhoneNumber());
    }

    @Override
    public void deleteCustomer(long id) {
        /*jdbcTemplate.update("DELETE FROM customers WHERE id = ?" + "VALUES (?)",
                id);*/
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findCustomersByNameAndSurname(String name, String surname) {
        return null;
    }

    @Override
    public List<Customer> findAllCustomersByName(String name) {
        return null;
    }

    @Override
    public List<Customer> findAllCustomersBySurname(String surname) {
        return null;
    }

    @Override
    public boolean containsCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean containsId(Long id) {
        return false;
    }
}
