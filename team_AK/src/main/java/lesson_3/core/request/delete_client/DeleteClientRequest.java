package lesson_3.core.request.delete_client;

import lombok.Getter;

public class DeleteClientRequest {
    @Getter
    private final long id;

    public DeleteClientRequest(long id) {
        this.id = id;
    }
}