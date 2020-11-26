package internet_store.core.response.product;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class ChangeProductResponse extends CoreResponse {

    private Long id;

    public ChangeProductResponse(Long id) {
        this.id = id;
    }

    public ChangeProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }
}
