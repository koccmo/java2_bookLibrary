package lesson_3.core.response.add_client.client_items;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddClientNameResponse extends CoreErrorResponse {
    @Getter
    private String name;

    public AddClientNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientNameResponse(String name) {
        this.name = name;
    }
}