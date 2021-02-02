package internet_store.core.request.cart;

import lombok.Getter;

public class DeleteProductFromCartRequest {
    @Getter
    private final long id;
    @Getter
    private final Object clientDatabase;
    @Getter
    private final String productTitle;

    public DeleteProductFromCartRequest(long id, Object clientDatabase, String productTitle) {
        this.id = id;
        this.clientDatabase = clientDatabase;
        this.productTitle = productTitle;
    }
}