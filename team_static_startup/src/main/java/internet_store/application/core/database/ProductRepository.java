package internet_store.application.core.database;

import internet_store.application.core.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Profile("hibernate")
@Transactional
public class ProductRepository implements Database{

    private final SessionFactory sessionFactory;

    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long add(Product product) {
        return (Long) sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean deleteByProductId(Long id) {
        return true;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public boolean deleteByProductName(String product) {
        return false;
    }

    @Override
    public List<Product> findByProductName(String name) {
        return null;
    }

    @Override
    public List<Product> findByProductDescription(String description) {
        return null;
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        Query query = (Query) sessionFactory.getCurrentSession().createQuery(
                "select p FROM Product p where name = : name AND description = : description");
        query.setParameter("name", name);
        query.setParameter("description", description);
        return query.getResultList();
    }

    @Override
    public List<Product> getProductList() {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return null;
    }

    @Override
    public boolean changeProductName(Long id, String newName) {
        return false;
    }

}
