package estore.database;

import estore.core.model.ProductCategory;

import java.util.List;

//@Component
public class OrmProductCategoryRepositoryImpl implements ProductCategoryRepository {
    @Override
    public List<ProductCategory> getDatabase() {
        return null;
    }

    @Override
    public boolean addNewCategory(ProductCategory category) {
        return false;
    }
}
