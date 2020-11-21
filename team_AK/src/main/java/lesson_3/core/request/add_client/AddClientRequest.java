package lesson_3.core.request.add_client;

import lesson_3.core.domain.Client;
import lombok.Getter;

public class AddClientRequest {
    @Getter
    private final Client client;

    public AddClientRequest(Client client) {
        this.client = client;
    }
}