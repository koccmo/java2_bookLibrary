package lv.estore.app.web_ui.controllers.deals;

import lv.estore.app.core.request.deals.AnyIdInDealRequest;
import lv.estore.app.core.responses.deals.GetDealsResponse;
import lv.estore.app.core.services.deals.FindDealsByProductIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindDealsByProductIdController {

    @Autowired
    private FindDealsByProductIdService service;

    @GetMapping(value = "deals/findDealsByProductId")
    public String showFindDealsByProductIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AnyIdInDealRequest());
        return "deals/findDealsByProductId";
    }

    @PostMapping("deals/findDealsByProductId")
    public String processFindDealsByProductIdRequest(@ModelAttribute(value = "request") AnyIdInDealRequest request,
                                           ModelMap modelMap) {
        GetDealsResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("deals", response.getDeals());
        }
        return "deals/findDealsByProductId";
    }
}
