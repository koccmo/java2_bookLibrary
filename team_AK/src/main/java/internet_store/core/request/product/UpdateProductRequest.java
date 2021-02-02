package internet_store.core.request.product;

import internet_store.database.interfaces.ProductDatabase;
import lombok.Getter;

public class UpdateProductRequest {
    @Getter
    private final long id;
    @Getter
    ProductDatabase productDatabase;

    public UpdateProductRequest(ProductDatabase productDatabase, long id) {
        this.productDatabase = productDatabase;
        this.id = id;
    }
}