package lv.estore.app.core.responses.deals;

import lv.estore.app.core.domain.Deal;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class GetDealsResponse extends CoreResponse {
    private List<Deal> deals;

    public GetDealsResponse(List<CoreError> errors, List<Deal> deals) {
        super(errors);
        this.deals = deals;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public boolean hasDeals() {
        return deals != null && !deals.isEmpty();
    }
}
