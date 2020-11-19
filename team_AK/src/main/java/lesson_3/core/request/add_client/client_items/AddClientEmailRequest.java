package lesson_3.core.request.add_client.client_items;

import lombok.Getter;

public class AddClientEmailRequest {
    @Getter
    private final String email;

    public AddClientEmailRequest(String email) {
        this.email = email;
    }
}