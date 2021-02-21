package lv.estore.app.web_ui.controllers.deals;

import lv.estore.app.core.request.deals.GetAllDealsRequest;
import lv.estore.app.core.responses.deals.GetDealsResponse;
import lv.estore.app.core.services.deals.GetAllDealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllDealsController {

    @Autowired private GetAllDealsService service;


    @GetMapping(value = "deals/getAllDeals")
    public String showAllDeals(ModelMap modelMap) {
        GetDealsResponse response = service.execute(
                new GetAllDealsRequest()
        );
        modelMap.addAttribute("deals", response.getDeals());
        return "deals/getAllDeals";
    }
}
