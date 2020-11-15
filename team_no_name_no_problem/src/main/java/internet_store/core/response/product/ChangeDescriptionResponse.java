package internet_store.core.response.product;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class ChangeDescriptionResponse extends CoreResponse {

    private long id;

    public ChangeDescriptionResponse(long id) {
        this.id = id;
    }

    public ChangeDescriptionResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getId(){
        return id;
    }
}
