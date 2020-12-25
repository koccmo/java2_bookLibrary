package estore.matchers;

import estore.domain.Product;
import org.mockito.ArgumentMatcher;

public class ProductMatcher implements ArgumentMatcher<Product> {
    private String name;
    private String description;
    private String category;

    public ProductMatcher(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().equals(name)
                && product.getDescription().equals(description)
                && product.getCategory().equals(category);
    }
}
