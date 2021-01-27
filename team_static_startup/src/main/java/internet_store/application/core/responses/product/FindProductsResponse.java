package internet_store.application.core.responses.product;

import internet_store.application.core.domain.Product;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

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
