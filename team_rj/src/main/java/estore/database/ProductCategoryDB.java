package estore.database;

import estore.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryDB {

    List<ProductCategory> getDatabase();
    boolean addNewCategory(ProductCategory category);

}
