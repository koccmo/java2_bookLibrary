package lesson_3.core.request.delete_product;

import lombok.Getter;

public class DeleteProductRequest {
    @Getter
    private final long id;

    public DeleteProductRequest(long id) {
        this.id = id;
    }
}