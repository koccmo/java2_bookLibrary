package internet_store.core.request.client;

import lombok.Getter;

public class DeleteClientRequest {
    @Getter
    private final long id;

    public DeleteClientRequest(long id) {
        this.id = id;
    }
}