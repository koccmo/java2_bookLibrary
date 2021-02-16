package lv.estore.app.core.responses.deals;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class RemoveDealResponse extends CoreResponse {

    private boolean dealRemoved;

    public RemoveDealResponse(boolean dealRemoved) {
        this.dealRemoved = dealRemoved;
    }

    public RemoveDealResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDealRemoved() {
        return dealRemoved;
    }
}
