package internet_store.core.response.product;

import internet_store.core.domain.Product;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class DeleteByOtherResponse extends CoreResponse {

    private boolean responseOfRequestToDelete;

    public DeleteByOtherResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteByOtherResponse(boolean responseOfRequestToDelete) {
    }

    public boolean getResponseOfRequestToDelete() {
        return responseOfRequestToDelete;
    }
}
