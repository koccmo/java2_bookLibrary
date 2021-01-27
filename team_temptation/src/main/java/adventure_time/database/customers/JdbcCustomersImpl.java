package adventure_time.database.customers;

import adventure_time.core.domain.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcCustomersImpl implements DatabaseCustomers {

    private static final String insertSQL = "insert into customers (name, email, phone, password, activity) values (?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean add(Customers customer) {
        try {
            jdbcTemplate.update(
                    insertSQL,
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
        return false;
    }

    @Override
    public boolean deactivate(Long id) {
        return false;
    }

    @Override
    public List<Customers> getCustomersList() {
        return null;
    }

    @Override
    public Optional<Customers> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Customers> findByEmail(String customerEmail) {
        return Optional.empty();
    }

    @Override
    public boolean updateCustomer(Customers customer, Long id) {
        return false;
    }

    @Override
    public List<Customers> findAllActiveCustomers() {
        return null;
    }

    @Override
    public List<Customers> findAllInactiveCustomers() {
        return null;
    }

    @Override
    public Long checkLogin(String email, String password) {
        return null;
    }

    @Override
    public Long checkLoginBeforeUpdate(String email, String password) {
        return null;
    }
}
