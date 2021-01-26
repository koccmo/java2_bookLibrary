package internet_store.application.core.database.customer;

import internet_store.application.core.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
@Profile("hibernate")
@Transactional
public class ORMCustomerRepository implements CustomerRepository {

    private final SessionFactory sessionFactory;

    public ORMCustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long addCustomer(Customer customer) {
        return (Long) sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    public boolean deleteByCustomerId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Customer where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Customer> findByFirstName(String customerName) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT Customer WHERE name = : name");
        query.setParameter("name", customerName);
        return query.getResultList();
    }

    @Override
    public Optional<Customer> findByCustomerId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public boolean changeFirstName(Long id, String newFirstName) {
        return false;
    }

}
