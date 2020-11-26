package internet_store.core.response.update_client;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
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