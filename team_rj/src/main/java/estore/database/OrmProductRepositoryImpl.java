package estore.database;

import estore.core.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> searchProductByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT product FROM Product product WHERE prodName = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Product> searchProductByCategory(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT product FROM Product product WHERE category_id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Product> searchProductById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT product FROM Product product WHERE id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public boolean addNewProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return true;
    }

    @Override
    public int removeProductByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Product WHERE prodName = :name");
        query.setParameter("name", name);
        int result = query.executeUpdate();
        return result;
    }

    @Override
    public int removeProductById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Product WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result;
    }

    @Override
    public List<Product> getDatabase() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT products FROM Product products", Product.class)
                .getResultList();
    }

    @Override
    public void updateProduct(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public int getDatabaseSize() {
        return 0;
    }
}
