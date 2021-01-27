package estore.database;

import estore.core.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class OrmProductRepositoryImpl implements ProductRepository {

//    @Autowired
//    private SessionFactory sessionFactory;

    @Override
    public List<Product> searchProductByName(String name) {
        return null;
    }

    @Override
    public List<Product> searchProductByCategory(String category) {
        return null;
    }

    @Override
    public List<Product> searchProductById(Long id) {
        return null;
    }

    @Override
    public boolean addNewProduct(Product product) {
        return false;
    }

    @Override
    public int removeProductByName(String name) {
        return 0;
    }

    @Override
    public int removeProductById(Long id) {
        return 0;
    }

    @Override
    public List<Product> getDatabase() {
        return null;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public int getDatabaseSize() {
        return 0;
    }
}
