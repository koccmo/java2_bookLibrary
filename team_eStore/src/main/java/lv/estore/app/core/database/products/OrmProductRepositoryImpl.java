package lv.estore.app.core.database.products;

import lv.estore.app.core.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional
public class OrmProductRepositoryImpl implements  ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addProduct(Product product) {
        return (Long) sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean updateProductById(Long id, String name, String description, BigDecimal price) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Product SET name = :name, description = :description, price = :price WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("description", description);
        query.setParameter("price", price);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean removeProductById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Product where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean removeProductByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Product where name = :name");
        query.setParameter("name", name);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Product findProductById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Product p WHERE id = :id");
        query.setParameter("id", id);
        List<Product> products = query.getResultList();
        return products.stream().findFirst().get();
    }

    @Override
    public List<Product> findProductsByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Product p where name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Product> findProductsByPrice(BigDecimal price) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select p FROM Product p where price = :price");
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Product> findProductsByNameAndPrice(String name, BigDecimal price) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select p FROM Product p where name = :name AND price = :price");
        query.setParameter("name", name);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Product> getAllProducts() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }
}
