package internet_store.application.core.database.customer;

import internet_store.application.core.domain.Customer;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
}
