package lv.estore.app.web_ui.controllers.deals;

import lv.estore.app.core.request.deals.UpdateDealByIdRequest;
import lv.estore.app.core.responses.deals.UpdateDealResponse;
import lv.estore.app.core.services.deals.UpdateDealByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateDealController {

    @Autowired
    private UpdateDealByIdService service;

    @GetMapping(value = "deals/updateDeal")
    public String showUpdateDealPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new UpdateDealByIdRequest());
        return "deals/updateDeal";
    }

    @PostMapping("deals/updateDeal")
    public String processUpdateDealRequest(@ModelAttribute(value = "request") UpdateDealByIdRequest request,
                                              ModelMap modelMap) {
        UpdateDealResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deals/updateDeal";
        } else {
            return "redirect:/deals/";
        }
    }
}
