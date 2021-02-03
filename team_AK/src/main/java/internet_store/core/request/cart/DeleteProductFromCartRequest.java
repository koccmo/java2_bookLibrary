package internet_store.core.request.cart;

import lombok.Getter;

public class DeleteProductFromCartRequest {
    @Getter
    private final long id;
    @Getter
    private final Object clientDatabase;

    public DeleteProductFromCartRequest(long id, Object clientDatabase) {
        this.id = id;
        this.clientDatabase = clientDatabase;
    }
}