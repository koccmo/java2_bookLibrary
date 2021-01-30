package estore.database;

import estore.core.domain.ProductCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmProductCategoryRepositoryImpl implements ProductCategoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductCategory> getDatabase() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT categories FROM ProductCategory categories", ProductCategory.class)
                .getResultList();
    }

    @Override
    public boolean addCategory(ProductCategory category) {
        sessionFactory.getCurrentSession().save(category);
        return true;
    }
}
