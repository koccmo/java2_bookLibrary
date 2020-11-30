package internet_store.core.response.ordering;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class DeleteOrderResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public DeleteOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteOrderResponse(long id) {
        this.id = id;
    }
}