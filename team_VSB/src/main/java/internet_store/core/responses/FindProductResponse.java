package internet_store.core.responses;

import internet_store.core.domain.Product;

import java.util.List;

public class FindProductResponse extends CoreResponse {

    private final List<Product> products;


    public FindProductResponse(List<CoreError> error, List<Product> products) {
        super(error);
        this.products = products;
    }

    public List<Product> getProducts() { return products; }
}
