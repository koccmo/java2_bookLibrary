package internet_store.core.request.find_by_order_number;

import lombok.Getter;

public class FindByOrderNumberRequest {
    @Getter
    private final int orderNumber;

    public FindByOrderNumberRequest(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}