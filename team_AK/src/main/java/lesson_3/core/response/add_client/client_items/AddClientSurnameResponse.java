package lesson_3.core.response.add_client.client_items;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddClientSurnameResponse extends CoreErrorResponse {
    @Getter
    private String surname;

    public AddClientSurnameResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddClientSurnameResponse(String surname) {
        this.surname = surname;
    }
}