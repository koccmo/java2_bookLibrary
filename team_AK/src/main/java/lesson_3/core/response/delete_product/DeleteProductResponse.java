package lesson_3.core.response.delete_product;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
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