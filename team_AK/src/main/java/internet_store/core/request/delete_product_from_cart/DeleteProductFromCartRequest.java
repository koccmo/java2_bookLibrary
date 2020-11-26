package internet_store.core.request.delete_product_from_cart;

import lombok.Getter;

public class DeleteProductFromCartRequest {
    @Getter
    private final long id;

    public DeleteProductFromCartRequest(long id) {
        this.id = id;
    }
}