package adventure_time.database.customers;

import adventure_time.core.domain.Customers;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

// @Component
/*
@Transactional
    декларативное управление границами транзакции (управление границами транзакций с помощью аннотации, а не кода)
    все public методы этого класса будут выполняться в рамках открытой транзакции:
        перед выполнением каждого public метода будет открываться транзакция к базе данных,
        после завершения работы метода транзакция будет закрываться,
        при успешном завершении метода транзакция будет комититься,
        в случае ошибки - откатываться
    https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html
 */


public class OrmCustomerImpl implements DatabaseCustomers {

    @Autowired
    private SessionFactory sessionFactory;

    /*

    @Override
	public boolean deleteById(Long id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"delete Book where id = :id");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result == 1;
	}

     */


    @Override
    public boolean add(Customers customer) {

        // sessionFactory.getCurrentSession().save(book);

        return false;
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

        /*
        return sessionFactory.getCurrentSession()
				.createQuery("SELECT b FROM Book b", Book.class)
				.getResultList();
         */

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
