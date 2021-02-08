package estore.matchers;

import estore.core.domain.Product;
import org.mockito.ArgumentMatcher;

public class ProductMatcher implements ArgumentMatcher<Product> {
    private String name;
    private String description;
    private String category;
    private int quantity;
    private double price;

    public ProductMatcher(String name, String description, String category, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().equals(name)
                && product.getDescription().equals(description)
                && product.getCategory().getCategory().equals(category);
    }
}
