package lesson_3.core.response.add_client.client_items;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddClientEmailResponse extends CoreErrorResponse {
    @Getter
    private String email;

    public AddClientEmailResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientEmailResponse(String email) {
        this.email = email;
    }
}