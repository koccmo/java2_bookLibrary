package internet_store.application.core.database.product;

import internet_store.application.core.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Component
@Profile("hibernate")
@Transactional
public class ORMProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    public ORMProductRepository(SessionFactory sessionFactory) {
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
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Product where name = : name AND description = : description");
        query.setParameter("name", product.getName());
        query.setParameter("description", product.getDescription());
        return query.executeUpdate() >= 0;
    }

    @Override
    public boolean deleteByProductName(String product) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Product WHERE name = : name");
        query.setParameter("name", product);
        return query.executeUpdate() > 0;
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
        CriteriaQuery<Product> query = sessionFactory.getCriteriaBuilder().createQuery(Product.class);
        query.from(Product.class);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
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
