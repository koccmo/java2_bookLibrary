package internet_store.core.request.product;

import internet_store.core.domain.Product;
import lombok.Getter;

public class DeleteProductRequest {
    @Getter
    private final long id;
    @Getter
    private final Product product;
    @Getter
    private final Object productDatabase;

    public DeleteProductRequest(Object productDatabase, Product product, long id) {
        this.productDatabase = productDatabase;
        this.product = product;
        this.id = id;
    }
}