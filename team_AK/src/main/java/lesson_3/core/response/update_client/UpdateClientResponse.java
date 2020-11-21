package lesson_3.core.response.update_client;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class UpdateClientResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public UpdateClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateClientResponse(long id) {
        this.id = id;
    }
}