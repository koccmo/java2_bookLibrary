package internet_store.core.request.add_client;

import internet_store.core.domain.Client;
import lombok.Getter;

public class AddClientRequest {
    @Getter
    private final Client client;

    public AddClientRequest(Client client) {
        this.client = client;
    }
}