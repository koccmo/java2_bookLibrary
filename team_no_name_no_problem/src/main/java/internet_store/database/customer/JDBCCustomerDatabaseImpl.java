package internet_store.database.customer;

import internet_store.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class JDBCCustomerDatabaseImpl implements CustomerDatabase{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query("SELECT * FROM customers", new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public void addCustomer(Customer customer) {
        jdbcTemplate.update("INSERT INTO customers(name, surname, address, email, phone)"
        + "VALUES (?, ?, ?, ?, ?)",
                customer.getName(), customer.getSurname(), customer.getAddress(), customer.getEmail(),
                customer.getPhoneNumber());
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        return jdbcTemplate.update(connection -> {
                    PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM customers WHERE ID = ?");
                    statement.setLong(1, id);
                    return statement;
                }) == 1;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try{
            Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id = ?",
                    new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class));
            return Optional.ofNullable(customer);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> findCustomersByNameAndSurname(String name, String surname) {
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE name LIKE ? AND surname LIKE ?");
            statement.setString(1, name);
            statement.setString(2, surname);
            return statement;
        }, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public List<Customer> findAllCustomersByName(String name) {
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE name LIKE ?");
            statement.setString(1, name);
            return statement;
        }, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public List<Customer> findAllCustomersBySurname(String surname) {
        return jdbcTemplate.query(connection -> {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE surname LIKE ?");
            statement.setString(1, surname);
            return statement;
        }, new BeanPropertyRowMapper<>(Customer.class));
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
