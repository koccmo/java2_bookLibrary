package lv.estore.app.web_ui.controllers.deals;

import lv.estore.app.core.request.deals.AddDealRequest;
import lv.estore.app.core.responses.deals.AddDealResponse;
import lv.estore.app.core.services.deals.AddDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddDealController {

    @Autowired
    private AddDealService service;

    @GetMapping(value = "deals/addDeal")
    public String showAddDealPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddDealRequest());
        return "deals/addDeal";
    }

    @PostMapping("deals/addDeal")
    public String processAddDaelRequest(@ModelAttribute(value = "request") AddDealRequest request, ModelMap modelMap) {
        AddDealResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "ldeals/addDea";
        } else {
            return "redirect:/deals/";
        }
    }
}
