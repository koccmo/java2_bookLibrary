package lv.estore.app.core.matchers;

import lv.estore.app.core.domain.Product;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;

public class ProductMatcher implements ArgumentMatcher<Product> {

    private String name;
    private String description;
    private BigDecimal price;

    public ProductMatcher(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().equals(name)
                && product.getDescription().equals(description)
                && product.getPrice().equals(price);
    }
}
