package lv.estore.app.core.responses.deals;

import lv.estore.app.core.domain.Deal;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class FindDealByIdResponse extends CoreResponse {
    private Deal deal;

    public FindDealByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindDealByIdResponse(final Deal deal) {
        this.deal = deal;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
}
