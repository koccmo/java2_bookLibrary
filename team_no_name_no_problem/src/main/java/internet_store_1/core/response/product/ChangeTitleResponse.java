package internet_store_1.core.response.product;

import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

import java.util.List;

public class ChangeTitleResponse extends CoreResponse {

    private long id;

    public ChangeTitleResponse(long id) {
        this.id = id;
    }

    public ChangeTitleResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getId(){
        return id;
    }
}
