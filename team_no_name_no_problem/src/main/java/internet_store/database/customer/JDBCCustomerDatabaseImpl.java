package internet_store.database.customer;

import internet_store.core.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
public class JDBCCustomerDatabaseImpl implements CustomerDatabase{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomers() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
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
        String sql = "DELETE FROM customers WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql,args) == 1;
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
        String sql = "SELECT * FROM customers WHERE name = ? AND surname = ?";
        Object[] args = new Object[]{name, surname};
        return jdbcTemplate.query(sql, args, new CustomerRowMapper());
    }

    @Override
    public List<Customer> findAllCustomersByName(String name) {
        String sql = "SELECT * FROM customers WHERE name = ?";
        Object[] args = new Object[]{name};
        return jdbcTemplate.query(sql, args, new CustomerRowMapper());
    }

    @Override
    public List<Customer> findAllCustomersBySurname(String surname) {
        String sql = "SELECT * FROM customers WHERE surname = ?";
        Object[] args = new Object[]{surname};
        return jdbcTemplate.query(sql, args, new CustomerRowMapper());
    }

    @Override
    public boolean containsCustomer(Customer customer) {
        String sql = "SELECT * FROM customers WHERE name = \"" + customer.getName() +
   "\" AND surname = \"" + customer.getSurname() + "\";";
        return jdbcTemplate.query(sql, new CustomerRowMapper()).size() == 1;
    }
    @Override
    public boolean containsId(Long id) {
        String sql = "SELECT * FROM customers WHERE id = " + id + ";";
        return jdbcTemplate.query(sql, new CustomerRowMapper()).size() == 1;
    }
}
