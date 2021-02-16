package lv.estore.app.core.services.deals;

import lv.estore.app.core.database.deals.DealsRepository;
import lv.estore.app.core.domain.Deal;
import lv.estore.app.core.request.deals.GetAllDealsRequest;
import lv.estore.app.core.responses.deals.GetDealsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllDealsService {

    @Autowired
    DealsRepository database;

    public GetDealsResponse execute(final GetAllDealsRequest request) {
        List<Deal> deals = database.getAllDeals();
        return new GetDealsResponse(null, deals);
    }
}
