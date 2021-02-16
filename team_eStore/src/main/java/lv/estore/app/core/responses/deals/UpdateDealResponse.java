package lv.estore.app.core.responses.deals;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class UpdateDealResponse extends CoreResponse {
    private boolean dealUpdated;

    public UpdateDealResponse(boolean dealUpdated) {
        this.dealUpdated = dealUpdated;
    }

    public UpdateDealResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDealUpdated() {
        return dealUpdated;
    }
}
