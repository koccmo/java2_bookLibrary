package internet_store.core.request.delete_order;

import lombok.Getter;

public class DeleteOrderRequest {
    @Getter
    private final long id;

    public DeleteOrderRequest(long id) {
        this.id = id;
    }
}