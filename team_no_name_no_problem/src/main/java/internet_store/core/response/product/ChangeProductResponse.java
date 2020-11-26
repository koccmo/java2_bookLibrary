package internet_store.core.response.product;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class ChangeProductResponse extends CoreResponse {

    private long id;

    public ChangeProductResponse(long id) {
        this.id = id;
    }

    public ChangeProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getId() {
        return id;
    }
}
