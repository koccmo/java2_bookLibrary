package internet_store_tests.core.services_tests.matchers;

import internet_store.core.domain.Product;
import org.mockito.ArgumentMatcher;

public class ProductMatcher implements ArgumentMatcher <Product> {

    private final String title;
    private final String description;
    private final Integer price;

    public ProductMatcher(String title, String description, Integer price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean matches(Product product) {
        return product.getTitle().equals(title) &&
                product.getDescription().equals(description) &&
                product.getPrice() == price;
    }
}
