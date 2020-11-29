package internet_store.core.response.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class UpdateProductResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public UpdateProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateProductResponse(long id) {
        this.id = id;
    }
}