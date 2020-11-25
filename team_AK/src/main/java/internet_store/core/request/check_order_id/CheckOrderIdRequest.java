package internet_store.core.request.check_order_id;

import lombok.Getter;

public class CheckOrderIdRequest {
    @Getter
    private final long id;

    public CheckOrderIdRequest(long id) {
        this.id = id;
    }
}