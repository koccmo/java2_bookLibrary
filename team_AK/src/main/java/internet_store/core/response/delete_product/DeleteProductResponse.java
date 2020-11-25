package internet_store.core.response.delete_product;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class DeleteProductResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public DeleteProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteProductResponse(long id) {
        this.id = id;
    }
}