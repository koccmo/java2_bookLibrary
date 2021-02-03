package internet_store.core.request.client;

import internet_store.core.domain.Client;
import lombok.Getter;

public class AddClientRequest {
    @Getter
    private final Client client;
    @Getter
    private final Object clientDatabase;

    public AddClientRequest(Client client, Object clientDatabase) {
        this.client = client;
        this.clientDatabase = clientDatabase;
    }
}