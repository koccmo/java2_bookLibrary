package lv.estore.app.core.responses.deals;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class AddDealResponse extends CoreResponse {

    private boolean dealAdded;

    public AddDealResponse(boolean dealAdded) {
        this.dealAdded = dealAdded;
    }

    public AddDealResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDealAdded(){
        return dealAdded;
    }
}
