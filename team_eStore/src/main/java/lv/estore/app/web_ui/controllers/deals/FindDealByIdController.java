package lv.estore.app.web_ui.controllers.deals;

import lv.estore.app.core.request.deals.AnyIdInDealRequest;
import lv.estore.app.core.responses.deals.FindDealByIdResponse;
import lv.estore.app.core.services.deals.FindDealByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindDealByIdController {

    @Autowired
    private FindDealByIdService service;

    @GetMapping(value = "deals/findDealById")
    public String showFindDealByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AnyIdInDealRequest());
        return "deals/findDealById";
    }

    @PostMapping("deals/findDealById")
    public String processFindDealByIdRequest(@ModelAttribute(value = "request") AnyIdInDealRequest request,
                                                ModelMap modelMap) {
        FindDealByIdResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("deal", response.getDeal());
        }
        return "deals/findDealById";
    }
}
