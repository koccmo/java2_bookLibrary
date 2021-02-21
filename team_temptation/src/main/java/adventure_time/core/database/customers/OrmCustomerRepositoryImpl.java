package adventure_time.core.database.customers;

import adventure_time.core.domain.Customers;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Transactional
//@Component
public class OrmCustomerRepositoryImpl implements BasicCustomerRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean add(Customers customer) {
        sessionFactory.getCurrentSession().save(customer);
        return true;
    }
}
