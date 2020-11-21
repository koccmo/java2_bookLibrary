package lesson_3.core.response.delete_client;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class DeleteClientResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public DeleteClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteClientResponse(long id) {
        this.id = id;
    }
}
