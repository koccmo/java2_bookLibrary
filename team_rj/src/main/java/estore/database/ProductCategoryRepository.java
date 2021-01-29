package estore.database;

import estore.core.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository {

    List<ProductCategory> getDatabase();

    boolean addCategory(ProductCategory category);

}
