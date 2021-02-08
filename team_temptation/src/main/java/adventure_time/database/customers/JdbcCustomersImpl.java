package adventure_time.database.customers;

import adventure_time.core.domain.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcCustomersImpl implements DatabaseCustomers {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean add(Customers customer) {
        try {
            jdbcTemplate.update(
                    "insert into customers (name, email, phone, password, activity) values (?, ?, ?, ?, ?)",
                    customer.getCustomerName(),
                    customer.getCustomerEmail(),
                    customer.getCustomerPhone(),
                    customer.getCustomerPassword(),
                    customer.getActivity());
        } catch (DuplicateKeyException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean activate(Long id) {
        String sql = "update customers set activity = true where id = ?";
        return (jdbcTemplate.update(sql, id) == 1);
    }

    @Override
    public boolean deactivate(Long id) {
        String sql = "update customers set activity = false where id = ?";
        return (jdbcTemplate.update(sql, id) == 1);
    }

    @Override
    public Optional<Customers> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from customers where id = ?",
                    new Object[]{id},
                    new CustomerRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customers> findByEmail(String customerEmail) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from customers where email = ?",
                    new Object[]{customerEmail},
                    new CustomerRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean updateCustomer(Customers customer, Long id) {
        String sql = "update customers set name = ?, set email = ?, set phone = ?, set password = ?, set activity = true where id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    @Override
    public List<Customers> findAllActiveCustomers() {

        return jdbcTemplate.query(
                "select * from customers where activity = true",
                new CustomerRowMapper());
    }

    @Override
    public List<Customers> findAllInactiveCustomers() {

        return jdbcTemplate.query(
                "select * from customers where activity = false",
                new CustomerRowMapper());
    }
}
