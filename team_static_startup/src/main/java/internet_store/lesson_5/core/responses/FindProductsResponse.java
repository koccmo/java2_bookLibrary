package internet_store.lesson_5.core.responses;

import internet_store.lesson_5.core.domain.Product;

import java.util.List;

public class FindProductsResponse extends CoreResponse {
    private final List<Product> products;

    public FindProductsResponse(List<Product> products, List<CoreError> errors) {
        super(errors);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

}
