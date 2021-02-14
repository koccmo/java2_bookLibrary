package lv.estore.app.web_ui.controllers.deals;

import lv.estore.app.core.request.deals.AnyIdInDealRequest;
import lv.estore.app.core.responses.deals.RemoveDealResponse;
import lv.estore.app.core.services.deals.RemoveDealByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveDealByIdController {

    @Autowired
    private RemoveDealByIdService service;

    @GetMapping(value = "deals/removeDealById")
    public String showRemoveDealByIdPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AnyIdInDealRequest());
        return "deals/removeDealById";
    }

    @PostMapping("deals/removeDealById")
    public String processRemoveDealByIdRequest(@ModelAttribute(value = "request") AnyIdInDealRequest request,
                                                  ModelMap modelMap) {
        RemoveDealResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "redirect:/products/";
        } else {
            modelMap.addAttribute("result", response.isDealRemoved());
            return "deals/removeDealById";
        }
    }
}
