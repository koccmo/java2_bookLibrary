package estore.database.inmemoryrepo;

import estore.database.ProductCategoryRepository;
import estore.core.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;

//@Component
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {

    private Long nextCategoryId;
    private List<ProductCategory> categories;
    public ProductCategoryRepositoryImpl() {
        initializeListOfCategories();
        setNextCategoryId();
    }

    private void initializeListOfCategories() {
        this.categories = new ArrayList<>();
        this.categories.add(new ProductCategory("Fruits"));
        this.categories.add(new ProductCategory("Vegetables"));
        this.categories.add(new ProductCategory("Bakery"));
        this.categories.add(new ProductCategory("Meat"));
        this.categories.add(new ProductCategory("Fish"));
        this.categories.add(new ProductCategory("Drinks"));
        this.categories.add(new ProductCategory("Alcohol"));
        this.categories.get(0).setId(1L);
        this.categories.get(1).setId(2L);
        this.categories.get(2).setId(3L);
        this.categories.get(3).setId(4L);
        this.categories.get(4).setId(5L);
        this.categories.get(5).setId(6L);
        this.categories.get(6).setId(7L);
    }

    @Override
    public List<ProductCategory> getDatabase() {
        return this.categories;
    }

    private void setNextCategoryId() {
        this.nextCategoryId = Long.valueOf(this.categories.size()) + 1;
    }

    @Override
    public boolean addCategory(ProductCategory category) {
        category.setId(this.nextCategoryId);
        this.nextCategoryId++;
        this.categories.add(category);
        return true;
    }
}
