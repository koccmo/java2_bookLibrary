package lesson_3.core.request.update_client;

import lombok.Getter;

public class UpdateClientRequest {
    @Getter
    private final long id;

    public UpdateClientRequest(long id) {
        this.id = id;
    }
}