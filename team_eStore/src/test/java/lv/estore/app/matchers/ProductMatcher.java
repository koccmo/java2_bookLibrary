package lv.estore.app.matchers;

import lv.estore.app.core.domain.Product;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;

public class ProductMatcher implements ArgumentMatcher<Product> {

    private String name;
    private BigDecimal price;

    public ProductMatcher(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().equals(name)
                && product.getPrice().equals(price);
    }
}
