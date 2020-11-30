package internet_store.core.response.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class DeleteProductFromCartResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public DeleteProductFromCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteProductFromCartResponse(long id) {
        this.id = id;
    }
}