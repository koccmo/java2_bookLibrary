package internet_store.core.request.update_product;

import lombok.Getter;

public class UpdateProductRequest {
    @Getter
    private final long id;

    public UpdateProductRequest(long id) {
        this.id = id;
    }
}