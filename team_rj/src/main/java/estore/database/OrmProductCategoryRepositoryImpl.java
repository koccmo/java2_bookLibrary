package estore.database;

import estore.domain.ProductCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class OrmProductCategoryRepositoryImpl implements ProductCategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductCategory> getDatabase() {
        List<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory("Fruits"));
        return categories;
//        return sessionFactory.getCurrentSession()
//                .createQuery("SELECT b FROM productCategory b", ProductCategory.class)
//                .getResultList();
    }

    @Override
    public boolean addNewCategory(ProductCategory category) {
        sessionFactory.getCurrentSession().save(category);
        return true;
    }
}
