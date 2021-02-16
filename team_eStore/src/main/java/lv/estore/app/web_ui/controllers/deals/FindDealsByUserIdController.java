package lv.estore.app.web_ui.controllers.deals;

import lv.estore.app.core.request.deals.AnyIdInDealRequest;
import lv.estore.app.core.responses.deals.GetDealsResponse;
import lv.estore.app.core.services.deals.FindDealsByUserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindDealsByUserIdController {

    @Autowired
    private FindDealsByUserIdService service;

    @GetMapping(value = "deals/findDealsByUserId")
    public String showFindDealsByUserIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AnyIdInDealRequest());
        return "deals/findDealsByUserId";
    }

    @PostMapping("deals/findDealsByUserId")
    public String processFindDealsByUserIdRequest(@ModelAttribute(value = "request") AnyIdInDealRequest request,
                                           ModelMap modelMap) {
        GetDealsResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("deals", response.getDeals());
        }
        return "deals/findDealsByUserId";
    }
}
