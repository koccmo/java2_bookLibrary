package internet_store.core.request.client;

import internet_store.core.domain.Client;
import lombok.Getter;

public class DeleteClientRequest {
    @Getter
    private final long id;
    @Getter
    private final Object clientDatabase;
    @Getter
    private final Client client;

    public DeleteClientRequest(long id, Object clientDatabase, Client client) {
        this.id = id;
        this.clientDatabase = clientDatabase;
        this.client = client;
    }
}