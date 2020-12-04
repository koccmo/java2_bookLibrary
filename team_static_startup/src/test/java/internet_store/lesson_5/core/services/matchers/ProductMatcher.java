package internet_store.lesson_5.core.services.matchers;

import internet_store.lesson_5.core.domain.Product;
import org.mockito.ArgumentMatcher;

import java.math.BigDecimal;

public class ProductMatcher implements ArgumentMatcher<Product> {
    private Long id;
    private String productName;
    private String productDescription;
    private BigDecimal price;

    public ProductMatcher(String productName, String productDescription, BigDecimal price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().equals(productName)
                && product.getDescription().equals(productDescription)
                && (product.getPrice().compareTo(price) == 0);
    }

}
