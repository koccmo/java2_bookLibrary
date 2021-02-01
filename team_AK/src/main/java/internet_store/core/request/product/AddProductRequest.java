package internet_store.core.request.product;

import internet_store.core.domain.Product;
import lombok.Getter;

public class AddProductRequest {
    @Getter
    private final Product product;
    @Getter
    private final Object productDatabase;

    public AddProductRequest(Object productDatabase, Product product) {
        this.productDatabase = productDatabase;
        this.product = product;
    }
}