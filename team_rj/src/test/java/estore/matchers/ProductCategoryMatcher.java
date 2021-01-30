package estore.matchers;

import estore.core.domain.ProductCategory;
import org.mockito.ArgumentMatcher;

public class ProductCategoryMatcher implements ArgumentMatcher<ProductCategory> {

    private String productCategory;

    public ProductCategoryMatcher(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean matches(ProductCategory productCategory) {
        return productCategory.getCategory().equals(productCategory);
    }
}
