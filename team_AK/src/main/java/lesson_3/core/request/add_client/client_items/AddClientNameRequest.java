package lesson_3.core.request.add_client.client_items;

import lombok.Getter;

public class AddClientNameRequest {
    @Getter
    private final String name;

    public AddClientNameRequest(String name) {
        this.name = name;
    }
}