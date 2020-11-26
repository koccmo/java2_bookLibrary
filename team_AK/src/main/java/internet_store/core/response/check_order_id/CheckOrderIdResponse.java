package internet_store.core.response.check_order_id;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class CheckOrderIdResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public CheckOrderIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public CheckOrderIdResponse(long id) {
        this.id = id;
    }
}