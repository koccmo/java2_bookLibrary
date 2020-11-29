package internet_store.core.response.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class UpdateCartResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public UpdateCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateCartResponse(long id) {
        this.id = id;
    }
}